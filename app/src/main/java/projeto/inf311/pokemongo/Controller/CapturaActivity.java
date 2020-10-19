package projeto.inf311.pokemongo.Controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import projeto.inf311.pokemongo.Model.Aparecimento;
import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.Model.Pokemon;
import projeto.inf311.pokemongo.R;
import projeto.inf311.pokemongo.View.CameraPreview;
import projeto.inf311.pokemongo.Util.MyApp;


public class CapturaActivity extends Activity implements SensorEventListener {

    public MediaPlayer mp;
    public ImageView pokebola;
    public int alturaPokemon;
    public int larguraPokemon;
    public int alturaPokebola;
    public int larguraPokebola;
    public int dimenX; //dimensao horizontal da tela em pixel
    public int dimenY; //dimensao vertical da tela em pixel
    private SensorManager sensorManager;
    private Sensor sensor;

    ImageView img;
    public float centerX; //centro horizontal ajustado
    public float centerY; //centro vertical ajustado
    public float escalaX; //usada para converter leituras em pixel
    public float escalaY; //usada para converter leituras em pixel

    public float grauXtotal = 0;
    public float grauYtotal = 0;
    public float grauZtotal = 0;
    public float grauXnovo = 0;
    public float grauYnovo = 0;
    public float grauZnovo = 0;
    public float grauXant = 0;
    public float grauYant = 0;
    public float grauZant = 0;
    Pokemon p;
    Aparecimento ap;

    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        }
        catch (Exception e){
        }
        return c;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(CapturaActivity.class != null) {
                        Intent intent = new Intent(this, CapturaActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    public void onStart() {
        super.onStart();
        mp.start();
    }

    public void onPause(){
        super.onPause();
        mp.pause();
        sensorManager.unregisterListener(this);
    }

    public void onResume(){
        super.onResume();
        mp.start();
        if(sensor != null){
        //Começa a escutar os sensores utilizados
            sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_GAME);
        }
        img.post(new Runnable() {
            @Override
            public void run() {
                //obtem o centro da tela
                centerX = dimenX / 2 - (img.getMeasuredWidth() / 2);
                centerY = dimenY / 2 - (img.getMeasuredHeight() / 2);
                //coloca o objeto no centro
                img.setX(centerX);
                img.setY(centerY);
                //calcula a escala em relação ao máximo possível nas medições
                escalaX = centerX / 36;
                escalaY = centerY / 36;
            }
        });

        pokebola.post
                (new Runnable() {
                    @Override
                    public void run() {
                        larguraPokebola = pokebola.getMeasuredWidth();
                        alturaPokebola = pokebola.getMeasuredHeight();
                        configuraTouchPokebola();
                    }
                });
    }

    public float xInicialTouch;
    public float yInicialTouch;
    //fazer point ou location pra calcular speed quando soltar pokebola

    public void configuraTouchPokebola() {
    //configura o listener de toque na pokebola
        pokebola.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //obtem local do toque na tela
                float x = event.getRawX();
                float y = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //tratamento se pressionou a imagem
                        //Toast.makeText(getBaseContext(), "Tocou imagem", Toast.LENGTH_SHORT).show();
                        xInicialTouch = x;
                        yInicialTouch = y;

                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        //tratamento se retirou o dedo da imagem
                        Toast.makeText(getBaseContext(), "Soltou a imagem", Toast.LENGTH_SHORT).show();
                        //aqui calcula speed
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //tratamento para arrastar imagem
                        pokebola.setX(((int) x) - (larguraPokebola / 2));
                        pokebola.setY(((int) y) - (alturaPokebola / 2));
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura);

        mp = MediaPlayer.create(this, R.raw.battle);
        mp.setLooping(true);

        Intent it = getIntent();
        p = (Pokemon) it.getSerializableExtra("pokemon");
        ap = (Aparecimento) it.getSerializableExtra("ap");

        img = (ImageView) findViewById(R.id.imgPokemon);
        img.setImageResource(p.getFoto());

        /*//Obtem gerenciador de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Obtem sensores a serem utilizados
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        Intent it = getIntent();
        Pokemon p = (Pokemon) it.getSerializableExtra("pokemon");

        Camera c = getCameraInstance();

        FrameLayout preview = (FrameLayout) findViewById(R.id.frame);
        CameraPreview cp = new CameraPreview(MyApp.getAppContext(), c);
        preview.addView(cp);

        img = (ImageView) findViewById(R.id.imgPokemon);
        img.setImageResource(p.getFoto());

        pokebola = (ImageView) findViewById(R.id.pokebola);
        pokebola.setImageResource(R.drawable.pokeball);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(this.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        dimenX = metrics.widthPixels;
        dimenY = metrics.heightPixels;


        int pontoInicial = dimenX/4;
        larguraPokemon = pontoInicial * 2;
        alturaPokemon = (larguraPokemon * img.getLayoutParams().height) / img.getLayoutParams().height;

        img.getLayoutParams().height = alturaPokemon;
        img.getLayoutParams().width = larguraPokemon;*/

    }
    public void teste(View v) {
        cg.getUser().capturar(ap);
    }

    public void onDestroy(){
        super.onDestroy();
        if(mp != null){
            mp.stop(); //PARA O SOM
            mp.release(); //LIBERA RECURSOS DO SISTEMA
        }
        finish();
    }

    //EU NAO CONSEGUI TESTAR NO EMULADOR, MAS COPIEI GRANDE PARTE DO SLIDE, PLEASE CHEQUEM E ESSA EH A PARTE DO POKEMON SOH

    public void onSensorChanged(SensorEvent event) {

        float x = (float) (event.values[0] * 57.2958);
        float y = (float) (event.values[1] * 57.2958);
        float z = (float) (event.values[2] * 57.2958);
        //obtem graus deslocados desde a ultima medição
        grauXnovo = (float) (x * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME
        grauYnovo = (float) (y * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME
        grauZnovo = (float) (z * 0.02); //0.02 segundos devido ao SENSOR_DELAY_GAME
        //total de graus deslocados desde o inicio
        grauXtotal += grauXnovo;
        grauYtotal += grauYnovo;
        grauZtotal += grauZnovo;
        //elimina pequenos ruidos do sensor com dispositivo parado
        if (!(grauYtotal > grauYant + 0.03 || grauYtotal < grauYant - 0.03))
            grauYtotal = grauYant;
        if (!(grauXtotal > grauXant + 0.03 || grauXtotal < grauXant - 0.03))
            grauXtotal = grauXant;
        if (!(grauZtotal > grauZant + 0.03 || grauZtotal < grauZant - 0.03))
            grauZtotal = grauZant;



        if(x == 180 || y == 180 || z == 180){
            //voltar pro centro (?)
            grauXtotal = 0;
            grauYtotal = 0;

        }
        //guarda medição para comparar com a proxima
        grauXant = grauXtotal;
        grauYant = grauYtotal;
        grauZant = grauZtotal;


        float xNovo = centerX - (escalaX * grauXtotal);
        float yNovo = centerY + (escalaY * grauYtotal);


        float xAnterior = img.getX();
        float yAnterior = img.getY();


        xNovo = xAnterior + ( (xNovo - xAnterior) * (float)0.2 );
        yNovo = yAnterior + ( (yNovo - yAnterior) * (float)0.2 );

        img.setX(xNovo);
        img.setY(yNovo);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }



}
