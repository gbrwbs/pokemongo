package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.Model.Pokemon;
import projeto.inf311.pokemongo.Model.PokemonCapturado;
import projeto.inf311.pokemongo.R;

public class MapCapturasActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener{

    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
    private GoogleMap map;
    public List<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_capturas);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapCapturas)).getMap();

        Intent it = getIntent();

        Map<Pokemon, List<PokemonCapturado>> capturasPokemons = cg.getUser().getPokemons();

        if (it.getStringExtra("variante").equals("TODAS_ESPECIES")){
            for (Pokemon pokemon : capturasPokemons.keySet()) {
                List<PokemonCapturado> capturas = capturasPokemons.get(pokemon);
                for (PokemonCapturado pkmnCap : capturas){
                    LatLng pospkmn = new LatLng(pkmnCap.getLatitude(), pkmnCap.getLongitude());
                    markers.add(map.addMarker(new MarkerOptions().position(pospkmn).icon(BitmapDescriptorFactory.fromResource(pokemon.getIcone()))));
                }
            }
        }
        else if (it.getStringExtra("variante").equals("UNICA_ESPECIE")){
            Pokemon pokemon = (Pokemon)it.getSerializableExtra("pokemon");
            List<PokemonCapturado> capturas = cg.getUser().getPokemons().get(pokemon);
            for (PokemonCapturado pkmnCap : capturas){
                LatLng pospkmn = new LatLng(pkmnCap.getLatitude(), pkmnCap.getLongitude());
                markers.add(map.addMarker(new MarkerOptions().position(pospkmn).icon(BitmapDescriptorFactory.fromResource(pokemon.getIcone()))));
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker){
        //TODO detalhes da captura ao clickar no marcador
        Toast.makeText(this, "Detalhes", Toast.LENGTH_LONG).show();
        return false;
    }
}
