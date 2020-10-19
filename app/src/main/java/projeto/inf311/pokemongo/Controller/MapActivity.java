package projeto.inf311.pokemongo.Controller;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import projeto.inf311.pokemongo.Model.Aparecimento;
import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.R;

public class MapActivity extends FragmentActivity implements LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    public LocationManager lm;
    public Criteria criteria;
    public String provider;
    public int TEMPO_REQUISICAO_LATLONG = 5000;
    public int DISTANCIA_MIN_MENTROS = 0;
    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();

    public MediaPlayer mp;
    public List<Marker> markers = new ArrayList<>();
    public List<Marker> icon = new ArrayList<>();
    public LatLng pos;
    boolean posicaoInicial = true;

    public Aparecimento[] pokemons = new Aparecimento[10];

    private int mInterval = 180000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    double deltaLong;
    double longMax;
    double longMin;
    double latMax;
    double latMin;

    //int idPok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ImageView imgPerfil = (ImageView) findViewById(R.id.imgPerfil);
        ImageView imgMapa = (ImageView) findViewById(R.id.imageView5);
        ImageView imgPokedex = (ImageView) findViewById(R.id.imageView6);

        imgMapa.setImageResource(R.drawable.mapa_captura);

        imgPokedex.setImageResource(R.drawable.pokedex);

        if(cg.getUser().getSexo().equals("Feminino"))
            imgPerfil.setImageResource(R.drawable.female_profile);
        else
            imgPerfil.setImageResource(R.drawable.male_profile);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mp = MediaPlayer.create(this, R.raw.tema_rota_1);
        mp.setLooping(true);


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        PackageManager packageManager = getPackageManager();
        boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

        if(hasGPS) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            Log.i("LOCATION", "Usando GPS");
        }else{
            Log.i("LOCATION", "Usando WIFI ou dados");
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        }
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    public void Perfil(View v){
        Intent it = new Intent(this, PerfilActivity.class);
        mp.pause();
        startActivity(it);
    }

    public void MapaCapturas(View v){
        mp.pause();
        Intent it = new Intent(this, MapCapturasActivity.class);
        it.putExtra("variante","TODAS_ESPECIES");
        startActivity(it);
    }

    public void Pokedex(View v){
        mp.pause();
        Intent it = new Intent(this, PokedexActivity.class);
        startActivity(it);
    }

     protected void onStart() {
        super.onStart();

        provider = lm.getBestProvider(criteria, true);

        if(provider == null) {
            Log.e("PROVEDOR", "Nenhum provedor encontrado");
        }else{
            Log.i("PROVEDOR", "Está sendo utilizado o provedor: " + provider);
        }

         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
             requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
         }
         if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
             lm.requestLocationUpdates(provider, TEMPO_REQUISICAO_LATLONG, DISTANCIA_MIN_MENTROS, this);
         } else {
             Toast.makeText(getBaseContext(), "Habilite GPS ou Internet!", Toast.LENGTH_LONG).show();
         }

         mp.start();
    }

    public void onPause(){
        super.onPause();
        mp.pause();
    }

    public void onResume(){
        super.onResume();
        mp.start();
    }


    public void onDestroy(){
        //ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
        cg.logoutUser();
        lm.removeUpdates(this);
        Log.w("PROVEDOR", "Provedor" + provider + "parado!");
        super.onDestroy();
        if(mp != null){
            mp.stop(); //PARA O SOM
            mp.release(); //LIBERA RECURSOS DO SISTEMA
        }
        stopRepeatingTask();
        finish();
    }


    public void onLocationChanged(Location location) {

        if(!icon.isEmpty()){
            for (Marker m:
                    icon) {
                m.remove();
            }
        }

        if(!markers.isEmpty()){
            for (Marker m:
                    markers) {
                m.remove();
            }
        }

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        // double velocidade = location.getSpeed() * 3.6;

        pos = new LatLng(latitude, longitude);


        double kmInLongitudeDegree = 111.320 * Math.cos( latitude / 180.0 * Math.PI);

        double deltaLat = 0.3 / 111.1;
        deltaLong = 0.3 / kmInLongitudeDegree;

        latMin = latitude - deltaLat;
        latMax = latitude + deltaLat;
        longMax = longitude - deltaLong;
        longMin = longitude + deltaLong;

        if(posicaoInicial){
            mHandler = new Handler();
            startRepeatingTask();
            posicaoInicial = false;

        }


        if (location != null) {

            LatLng loc = new LatLng(latitude, longitude);

            if (cg.getUser().getSexo().equals("Masculino"))
                icon.add(map.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromResource(R.drawable.male))));
            else
                icon.add(map.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromResource(R.drawable.female))));

            map.setOnMarkerClickListener(this);


            CameraUpdate c = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(loc).tilt(60).zoom(18).build());
            map.animateCamera(c);


            for (Aparecimento ap : pokemons) {
                LatLng pospkmn = new LatLng(ap.getLatitude(), ap.getLongitude());
                markers.add(map.addMarker(new MarkerOptions().position(pospkmn).icon(BitmapDescriptorFactory.fromResource(ap.getPokemon().getIcone()))));

            }
        }
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {

            cg.sorteiaAparecimentos(latMin, latMax, longMin, longMax);
            pokemons = cg.getAparecimentos();

            if(!markers.isEmpty()){
                for (Marker m:
                        markers) {
                    m.remove();
                }
            }

            for (Aparecimento ap : pokemons) {

                LatLng pospkmn = new LatLng(ap.getLatitude(), ap.getLongitude());
                markers.add(map.addMarker(new MarkerOptions().position(pospkmn).icon(BitmapDescriptorFactory.fromResource(ap.getPokemon().getIcone()))));

            }

            mHandler.postDelayed(mStatusChecker, mInterval);

        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
   public boolean onMarkerClick(Marker marker) {
        Intent it = new Intent(getBaseContext(), CapturaActivity.class);
        Location posPokemon = new Location(provider);
        Location posicaoJogador = new Location(provider);

        posPokemon.setLatitude(marker.getPosition().latitude);
        posPokemon.setLongitude(marker.getPosition().longitude);

        posicaoJogador.setLatitude(pos.latitude);
        posicaoJogador.setLongitude(pos.longitude);

        double distancia = posicaoJogador.distanceTo(posPokemon); // em metros
        //int idPok;
        if(distancia <= 4000.0){
            for(Aparecimento ap: pokemons){
                if(ap.getLatitude() == posPokemon.getLatitude() && ap.getLongitude() == posPokemon.getLongitude()){
                    it.putExtra("pokemon", ap.getPokemon());
                    it.putExtra("ap", ap);
                }
            }
            mp.pause();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, 1);
            } else {
                //Intent intent = new Intent(this, CapturaActivity.class);
                startActivity(it);
            }
            //startActivity(it);

        }else{
            Toast.makeText(this, "Você está a " + distancia + " metros do Pokemon. Aproxime-se " + (distancia - 40.0) + " metros", Toast.LENGTH_LONG).show();
        }
        return false;
    }


}



