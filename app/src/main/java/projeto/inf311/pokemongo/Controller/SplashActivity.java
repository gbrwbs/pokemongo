package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.webkit.WebView;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.R;
import projeto.inf311.pokemongo.Util.BancoDadosSingleton;
import projeto.inf311.pokemongo.Util.MyApp;

public class SplashActivity extends Activity {
    public MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        WebView webView = (WebView) findViewById(R.id.loader);
        webView.loadUrl("file:///android_asset/loading.gif");
        webView.setBackgroundColor(Color.TRANSPARENT);

        mp = MediaPlayer.create(this, R.raw.abertura2);

        mp.start();

        final Intent it;

        ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
        //BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();

        if (cg.temSessao())
            it = new Intent(MyApp.getAppContext(), MapActivity.class);
        else
            it = new Intent(MyApp.getAppContext(), LoginActivity.class);


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override //Executa quando a musica termina a sua execução
            public void onCompletion(MediaPlayer mediaPlayer) {
                startActivity(it);
                finish();
            }
        });
    }

    //so esta funcionando a versao offline do app do professor
    public boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public void onDestroy(){
        super.onDestroy();
        if(mp != null){
            mp.stop(); //PARA O SOM
            mp.release(); //LIBERA RECURSOS DO SISTEMA
        }
    }
}
