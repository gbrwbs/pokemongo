package projeto.inf311.pokemongo.Model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projeto.inf311.pokemongo.Util.BancoDadosSingleton;
import projeto.inf311.pokemongo.Util.TimeUtil;

/**
 * Created by vanessa on 04/05/17.
 */

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private String sexo;
    private String foto;
    private String dtCadastro;
    private Map<Pokemon, List<PokemonCapturado>> pokemons = new HashMap<Pokemon, List<PokemonCapturado>>();

    protected Usuario(String lg){
        this.login = lg;
        this.preencherCapturas();
    }

    private void preencherCapturas(){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();

        Cursor c = bd.buscar("pokemon p, usuario u, pokemonusuario pu",new String[]{"p.idPokemon id", "pu.latitude lat", "pu.longitude long", "pu.dtCaptura dt"},"u.login = pu.login AND p.idPokemon = pu.idPokemon","");

        ControladoraFachadaSingleton instance = ControladoraFachadaSingleton.getINSTANCE();

        List<Pokemon> listPkmn = instance.getPokemons();

        for (Pokemon p: listPkmn) {
            int num = p.getNumero();
            while (c.moveToNext()){
                int idP = c.getColumnIndex("id");
                if (num == c.getInt(idP)){
                    int latitude = c.getColumnIndex("lat");
                    int longitude = c.getColumnIndex("long");
                    int dtCaptura = c.getColumnIndex("dt");
                    PokemonCapturado pc = new PokemonCapturado();
                    pc.setLatitude(c.getDouble(latitude));
                    pc.setLongitude(c.getDouble(longitude));
                    pc.setDtCaptura(c.getString(dtCaptura));
                    if(!pokemons.containsKey(p)){
                        List<PokemonCapturado> capturados= new ArrayList<>();
                        pokemons.put(p, capturados);
                    }
                    pokemons.get(p).add(pc);

                }
            }
        }
    }

    public boolean capturar(Aparecimento pkmn){

        Pokemon pkmnAux = pkmn.getPokemon();

        Map<String, String> ts = TimeUtil.getHoraMinutoSegundoDiaMesAno();
        String data = new String();
        for (String d:ts.keySet()) {
            data = d + " " + ts.get(d);
        }

        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();

        ContentValues valores = new ContentValues();
        valores.put("login", this.login);
        valores.put("idPokemon", pkmnAux.getNumero());
        valores.put("latitude", pkmn.getLatitude());
        valores.put("longitude", pkmn.getLongitude());
        valores.put("dtCaptura", data);

        bd.inserir("pokemonusuario", valores);

        PokemonCapturado pc = new PokemonCapturado();
        double latitude = pkmn.getLatitude();
        double longitude = pkmn.getLongitude();
        pc.setLatitude(latitude);
        pc.setLongitude(longitude);
        pc.setDtCaptura(data);
        if(!pokemons.containsKey(pkmnAux)){
            List<PokemonCapturado> capturados= new ArrayList<>();
            pokemons.put(pkmnAux, capturados);
        }
        pokemons.get(pkmnAux).add(pc);

        return true;

    }

    public int getQuantidadeCapturas(Pokemon pkmn){ return pokemons.get(pkmn).size(); }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() { return senha; }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(String dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Map<Pokemon, List<PokemonCapturado>> getPokemons(){ return this.pokemons; }


}
