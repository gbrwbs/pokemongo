package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.R;
import projeto.inf311.pokemongo.Util.BancoDadosSingleton;
import projeto.inf311.pokemongo.Util.TimeUtil;

public class CadastrarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        ImageView voltar = (ImageView) findViewById(R.id.imageView2);
        voltar.setImageResource(R.drawable.ic_action_back);
    }

    public void Cadastrar(View v){
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        EditText edtConfSenha = (EditText) findViewById(R.id.edtConfSenha);

        RadioGroup rg = (RadioGroup) findViewById(R.id.rgopcoes);
        int selecionado = rg.getCheckedRadioButtonId();
        RadioButton btnSexo = (RadioButton) findViewById(selecionado);

        String nome = edtNome.getText().toString();
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();
        String confSenha = edtConfSenha.getText().toString();
        String sexo = btnSexo.getText().toString();

        if (!verificaConexao()){
            Toast.makeText(this, "Verifique conexão com a Internet!!", Toast.LENGTH_LONG).show();
        }

        if(nome == null){
            Toast.makeText(this, "Informe um nome com até 50 caracteres!", Toast.LENGTH_LONG).show();
        }
        else{
            if(login == null){
                Toast.makeText(this, "Informe um usuário com até 45 caracteres!", Toast.LENGTH_LONG).show();
            }
            else{
                if(!login.matches("[a-zA-Z0-9]+")){
                    Toast.makeText(this, "Informe um usuário que contenha apenas letras e/ou números!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (senha == null) {
                        Toast.makeText(this, "Informe uma senha com até 45 caracteres!", Toast.LENGTH_LONG).show();
                    } else {
                        if(!senha.matches("[a-zA-Z0-9]+")){
                            Toast.makeText(this, "Informe uma senha que contenha apenas letras e/ou números!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            if (confSenha == null) {
                                Toast.makeText(this, "Informe a confirmação da senha!", Toast.LENGTH_LONG).show();
                            } else {
                                if (!senha.equals(confSenha)) {
                                    Toast.makeText(this, "Confirmação de senha inválida!" + "/n" + "Digite-a novamente", Toast.LENGTH_LONG).show();
                                } else {
                                    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
                                    if(!cg.cadastrarUser(login, nome, senha, sexo, "")){
                                        Toast.makeText(this, "Usuário já existente! Escolha outro…", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Intent it = new Intent(this, MapActivity.class);
                                        Toast.makeText(this, "Usuário cadastrado com sucesso!!", Toast.LENGTH_LONG).show();
                                        startActivity(it);
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void Voltar(View v){
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
        finish();
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
