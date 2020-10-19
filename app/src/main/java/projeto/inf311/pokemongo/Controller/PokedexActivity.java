package projeto.inf311.pokemongo.Controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projeto.inf311.pokemongo.Model.ControladoraFachadaSingleton;
import projeto.inf311.pokemongo.Model.Pokemon;
import projeto.inf311.pokemongo.Model.PokemonCapturado;
import projeto.inf311.pokemongo.R;
import projeto.inf311.pokemongo.Util.BancoDadosSingleton;
import projeto.inf311.pokemongo.Util.CustomAdapter;
import projeto.inf311.pokemongo.Util.MyApp;

public class PokedexActivity extends Activity {

    public ControladoraFachadaSingleton cg = ControladoraFachadaSingleton.getINSTANCE();
    private ListView listView;
    Pokemon p1 = new Pokemon();
    boolean[] flag = new boolean[151];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();

        Cursor c = bd.buscar("pokemon", new String[]{"idPokemon", "nome", "icone"}, "", "idPokemon asc");

        String[] nomes = new String[151];
        int[] images = new int[151];
        String[] posicoes = new String[151];

        Map<Pokemon, List<PokemonCapturado>> mapa = cg.getUser().getPokemons();
        int n = mapa.size();
        int faltam = 150 - n;

        TextView nCapturados = (TextView) findViewById(R.id.capturados);
        nCapturados.setText("Capturados: " + n + " Faltam: " + faltam);

        ImageView voltar = (ImageView) findViewById(R.id.imageView3);
        voltar.setImageResource(R.drawable.ic_action_back);

        if (c.getCount() > 0) {
            int cont = 0;
            while (c.moveToNext()) {
                int idPok = c.getColumnIndex("idPokemon");
                int nome = c.getColumnIndex("nome");
                int icone = c.getColumnIndex("icone");

                p1.setNumero(c.getInt(idPok));
                p1.setNome(c.getString(nome));
                p1.setIcone(c.getInt(icone));

                if (mapa.containsKey(p1)) {
                    nomes[cont] = p1.getNome();
                    images[cont] = p1.getIcone();
                    flag[cont] = true;

                } else {
                    nomes[cont] = "???";
                    images[cont] = R.drawable.help;
                }

                posicoes[cont] = "#" + p1.getNumero();
                cont++;
            }

        } else {
            for (int i = 0; i < 151; i++) {
                nomes[i] = "lala";
                images[i] = R.drawable.help;
                posicoes[i] = i + "";
            }
        }


        CustomAdapter adapter = new CustomAdapter(MyApp.getAppContext(), nomes, images, posicoes);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {

            @Override
            public void onItemClick (AdapterView < ? > adapter, View view,int position, long id){
                if(flag[position]) {
                    Intent intent = new Intent(getApplicationContext(), DetalhesPokedexActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }
        });
    }

    public void VoltarMapa(View v) {
        finish();
    }



}
