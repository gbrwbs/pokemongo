package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.Model.Pokemon;
import projeto.inf311.pokemongo.Model.Tipo;
import projeto.inf311.pokemongo.R;
import projeto.inf311.pokemongo.Util.BancoDadosSingleton;

public class DetalhesPokedexActivity extends Activity {
    ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
    Pokemon p1 = new Pokemon();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokedex);

        Intent it = getIntent();
        int position = it.getIntExtra("position",0)+1;
        String where = "idPokemon == " + position;

        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("pokemon", new String[]{"nome","categoria", "foto"}, where, "");


        while(c.moveToNext()) {
            int categoria = c.getColumnIndex("categoria");
            int nome = c.getColumnIndex("nome");
            int foto = c.getColumnIndex("foto");

            p1.setNumero(position);
            p1.setCategoria(c.getString(categoria));
            p1.setNome(c.getString(nome));
            p1.setFoto(c.getInt(foto));
        }

        String where2 = "p.idPokemon == " + position + " AND p.idTipo == t.idTipo";
        Cursor c2 = bd.buscar("pokemontipo p, tipo t", new String[]{"t.nome"}, where2, "");
        List<String> tipos = new ArrayList<String>();

        while(c2.moveToNext()) {
            int nome = c2.getColumnIndex("nome");
            tipos.add(c2.getString(nome));
        }
        TextView tipo1 = (TextView) findViewById(R.id.tipo1);
        tipo1.setText(tipos.get(0));

        switch (tipos.get(0)) {
            case "Normal":
                tipo1.setBackgroundColor(Color.parseColor("#a8a878"));
                break;
            case "Fire":
                tipo1.setBackgroundColor(Color.parseColor("#f08030"));
                break;
            case "Fighting":
                tipo1.setBackgroundColor(Color.parseColor("#c03028"));
                break;
            case "Water":
                tipo1.setBackgroundColor(Color.parseColor("#6890f0"));
                break;
            case "Flying":
                tipo1.setBackgroundColor(Color.parseColor("#a890f0"));
                break;
            case "Grass":
                tipo1.setBackgroundColor(Color.parseColor("#78c850"));
                break;
            case "Poison":
                tipo1.setBackgroundColor(Color.parseColor("#a040a0"));
                break;
            case "Electric":
                tipo1.setBackgroundColor(Color.parseColor("#f8d030"));
                break;
            case "Ground":
                tipo1.setBackgroundColor(Color.parseColor("#e0c068"));
                break;
            case "Psychic":
                tipo1.setBackgroundColor(Color.parseColor("#f85888"));
                break;
            case "Rock":
                tipo1.setBackgroundColor(Color.parseColor("#b8a038"));
                break;
            case "Ice":
                tipo1.setBackgroundColor(Color.parseColor("#98d8d8"));
                break;
            case "Bug":
                tipo1.setBackgroundColor(Color.parseColor("#a8b820"));
                break;
            case "Dragon":
                tipo1.setBackgroundColor(Color.parseColor("#7038f8"));
                break;
            case "Ghost":
                tipo1.setBackgroundColor(Color.parseColor("#705898"));
                break;
            case "Dark":
                tipo1.setBackgroundColor(Color.parseColor("#705848"));
                break;
            case "Steel":
                tipo1.setBackgroundColor(Color.parseColor("#b8b8d0"));
                break;
            case "Fairy":
                tipo1.setBackgroundColor(Color.parseColor("#ee99ac"));
                break;
        }

        if(tipos.size()>1){
            TextView tipo2 = (TextView) findViewById(R.id.tipo2);
            tipo2.setText(tipos.get(1));
            switch (tipos.get(1)) {
                case "Normal":
                    tipo2.setBackgroundColor(Color.parseColor("#a8a878"));
                    break;
                case "Fire":
                    tipo2.setBackgroundColor(Color.parseColor("#f08030"));
                    break;
                case "Fighting":
                    tipo2.setBackgroundColor(Color.parseColor("#c03028"));
                    break;
                case "Water":
                    tipo2.setBackgroundColor(Color.parseColor("#6890f0"));
                    break;
                case "Flying":
                    tipo2.setBackgroundColor(Color.parseColor("#a890f0"));
                    break;
                case "Grass":
                    tipo2.setBackgroundColor(Color.parseColor("#78c850"));
                    break;
                case "Poison":
                    tipo2.setBackgroundColor(Color.parseColor("#a040a0"));
                    break;
                case "Electric":
                    tipo2.setBackgroundColor(Color.parseColor("#f8d030"));
                    break;
                case "Ground":
                    tipo2.setBackgroundColor(Color.parseColor("#e0c068"));
                    break;
                case "Psychic":
                    tipo2.setBackgroundColor(Color.parseColor("#f85888"));
                    break;
                case "Rock":
                    tipo2.setBackgroundColor(Color.parseColor("#b8a038"));
                    break;
                case "Ice":
                    tipo2.setBackgroundColor(Color.parseColor("#98d8d8"));
                    break;
                case "Bug":
                    tipo2.setBackgroundColor(Color.parseColor("#a8b820"));
                    break;
                case "Dragon":
                    tipo2.setBackgroundColor(Color.parseColor("#7038f8"));
                    break;
                case "Ghost":
                    tipo2.setBackgroundColor(Color.parseColor("#705898"));
                    break;
                case "Dark":
                    tipo2.setBackgroundColor(Color.parseColor("#705848"));
                    break;
                case "Steel":
                    tipo2.setBackgroundColor(Color.parseColor("#b8b8d0"));
                    break;
                case "Fairy":
                    tipo2.setBackgroundColor(Color.parseColor("#ee99ac"));
                    break;
            }



        }

        TextView titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText("Detalhes " + p1.getNome());

        TextView num = (TextView) findViewById(R.id.numero);
        num.setText("#" + p1.getNumero());

        TextView nome= (TextView) findViewById(R.id.nome);
        nome.setText(p1.getNome());


        TextView nCapturados = (TextView) findViewById(R.id.ncapturados);
        nCapturados.setText(String.valueOf(cg.getUser().getQuantidadeCapturas(p1)));

        ImageView imagem = (ImageView) findViewById(R.id.imageView9);
        imagem.setImageResource(p1.getFoto());

        ImageView voltar = (ImageView) findViewById(R.id.imageView4);
        voltar.setImageResource(R.drawable.ic_action_back);
    }

    public void VoltarMapa(View v){
        finish();
    }

    public void locais(View v) {
        Intent it = new Intent(getApplicationContext(), MapCapturasActivity.class);
        it.putExtra("variante", "UNICA_ESPECIE");
        it.putExtra("pokemon", p1);
        startActivity(it);
    }

}
