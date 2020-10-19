package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.R;

public class LoginActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Cadastrar(View v) {
        Intent it = new Intent(this, CadastrarActivity.class);
        startActivity(it);
        finish();
    }

    public void Login(View v) {
        EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (login == null || senha == null) {
            Toast.makeText(this, "Usuário e/ou senha inválido(s)!", Toast.LENGTH_LONG).show();
        } else {
            if (login != null && !login.matches("[a-zA-Z0-9]+")) {
                Toast.makeText(this, "Informe um usuário válido!", Toast.LENGTH_LONG).show();
            } else {
                if (senha != null && !senha.matches("[a-zA-Z0-9]+")) {
                    Toast.makeText(this, "Informe uma senha válida!", Toast.LENGTH_LONG).show();
                } else {
                    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
                    if (!cg.loginUser(login, senha)) {
                        Toast.makeText(this, "Usuário e/ou senha inválido(s)!", Toast.LENGTH_LONG).show();
                    } else {
                    /*
                     Fazer quando implementar servidor
                     if(!cg.temSessao()){

                    }
                    */
                        if (!verificaConexao()){
                            Toast.makeText(this, "Verifique conexão com a Internet!!", Toast.LENGTH_LONG).show();
                        }
                        Intent it = new Intent(this, MapActivity.class);
                        startActivity(it);
                        finish();
                    }
                }
            }
        }
    }
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
}