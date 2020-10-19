package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.R;

public class PerfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();

        TextView txtLogin = (TextView) findViewById(R.id.txtLogin);
        TextView txtDtInicio = (TextView) findViewById(R.id.txtDtInicio);
        TextView txtCapturas = (TextView) findViewById(R.id.txtCapturas);

        txtLogin.setText(cg.getUser().getLogin());
        txtDtInicio.setText(cg.getUser().getDtCadastro());
        txtCapturas.setText("0");

        ImageView img = (ImageView) findViewById(R.id.imageView7);

        if(cg.getUser().getSexo().equals("Feminino"))
            img.setImageResource(R.drawable.female_grande);
        else
            img.setImageResource(R.drawable.male_grande);

        ImageView voltar = (ImageView) findViewById(R.id.imageView8);
        voltar.setImageResource(R.drawable.ic_action_back);

    }


    public void Logout(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("SAIR");
        alerta.setMessage("Deseja finalizar essa sessão?");

        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent it = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(it);
                finish();
            }
        });

        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        alerta.show();

    }
    public void VoltarMapa(View v){
        finish();
    }

}
