package projeto.inf311.pokemongo.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projeto.inf311.pokemongo.Util.BancoDadosSingleton;
import projeto.inf311.pokemongo.Util.RandomUtil;
import projeto.inf311.pokemongo.Util.TimeUtil;

/**
 * Created by vanessa on 04/05/17.
 */

public final class ControladoraFachadaSingleton {
    private Usuario user;
    private Map<String/*categoria*/, List<Pokemon>> pokemons = new HashMap<>();
    private Aparecimento[] aparecimentos = new Aparecimento[10];
    private List<Tipo> tiposPokemon = new ArrayList<>();
    private static ControladoraFachadaSingleton INSTANCE = new ControladoraFachadaSingleton();
    private boolean sorteouLendario = false;


    private ControladoraFachadaSingleton(){
        this.daoTipos();
        this.daoPokemons(this);
    }

    private void daoTipos(){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("tipo", new String[]{"idTipo","nome"}, "", "");

        while (c.moveToNext()){
            int idT = c.getColumnIndex("idTipo");
            int nome = c.getColumnIndex("nome");

            Tipo t = new Tipo();
            t.setIdTipo(c.getInt(idT));
            t.setNome(c.getString(nome));
            tiposPokemon.add(t);
        }
        c.close();
    }

    private void daoPokemons(ControladoraFachadaSingleton controladorGeral){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("pokemon", new String[]{"idPokemon","nome", "categoria", "foto", "icone"}, "", "");

        while (c.moveToNext()){
            int idP = c.getColumnIndex("idPokemon");
            int nome = c.getColumnIndex("nome");
            int categoria = c.getColumnIndex("categoria");
            int foto = c.getColumnIndex("foto");
            int icone = c.getColumnIndex("icone");

            Pokemon p = new Pokemon(c.getInt(idP), c.getString(nome), c.getString(categoria), c.getInt(foto), c.getInt(icone), controladorGeral);
            String cat = p.getCategoria();
            if(!pokemons.containsKey(cat)){
                List<Pokemon> listPokmn = new ArrayList<>();
                pokemons.put(cat, listPokmn);
            }
            pokemons.get(cat).add(p);
        }
        c.close();
    }

    private void daoUsuario(){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("usuario", new String[]{"login","senha", "nome", "sexo", "foto", "dtCadastro"}, "", "");
        //supoe que ha apenas um usuario no banco
        while (c.moveToNext()){
            int login = c.getColumnIndex("login");
            int senha = c.getColumnIndex("senha");
            int nome = c.getColumnIndex("nome");
            int sexo = c.getColumnIndex("sexo");
            int foto = c.getColumnIndex("foto");
            int dtCadastro = c.getColumnIndex("dtCadastro");

            user = new Usuario(c.getString(login));
            user.setSenha(c.getString(senha));
            user.setNome(c.getString(nome));
            user.setSexo(c.getString(sexo));
            user.setFoto(c.getString(foto));
            user.setDtCadastro(c.getString(dtCadastro));
        }
        c.close();
    }

    public static ControladoraFachadaSingleton getINSTANCE(){
        return INSTANCE;
    }

    public Usuario getUser() {
        return user;
    }

    //not sure about it, provavelmente ha pokemons repetidos
    public List<Pokemon> getPokemons() {
        List<Pokemon> listpkmn = new ArrayList<Pokemon>();
        for (String categoria: pokemons.keySet()) {
            listpkmn.addAll(pokemons.get(categoria));
        }
        return listpkmn;
    }

    public Aparecimento[] getAparecimentos() {
        return aparecimentos;
    }

    protected List<Tipo> getTiposPokemon() {
        return this.tiposPokemon;
    }

    public void sorteiaAparecimentos(double LatMin, double LatMax, double LongMin, double LongMax){
        int tamComum = pokemons.get("C").size();
        int tamIncomum = pokemons.get("I").size();
        int tamRaro = pokemons.get("R").size();
        int tamLendario = pokemons.get("L").size();
        Map<String, String> tempo = TimeUtil.getHoraMinutoSegundoDiaMesAno();
        int numSorteado = RandomUtil.randomIntInRange(0, 10);
        int numSorteado2 = RandomUtil.randomIntInRange(11, 20);

        int somaMinSegAtual = 0;
        for (String d:tempo.keySet()) {
            String [] aux = tempo.get(d).split(":");
            somaMinSegAtual = Integer.parseInt(aux[1]) + Integer.parseInt(aux[2]);
        }
        int contAp = 0;

        int sorteio = RandomUtil.randomIntInRange(0, tamRaro - 1);
        double latitude = RandomUtil.randomDoubleInRange(LatMin, LatMax);
        double longitude = RandomUtil.randomDoubleInRange(LongMin, LongMax);
        Pokemon p = pokemons.get("R").get(sorteio);
        Aparecimento apar = new Aparecimento();
        apar.setLatitude(latitude);
        apar.setLongitude(longitude);
        apar.setPokemon(p);
        aparecimentos[contAp] = apar;
        contAp++;

        if(!sorteouLendario && ((numSorteado % 2) == 0) && ((numSorteado2 % 2) == 0) && ((somaMinSegAtual % 2) != 0)) {
            sorteouLendario = true;
            sorteio = RandomUtil.randomIntInRange(0, tamLendario - 1);
            latitude = RandomUtil.randomDoubleInRange(LatMin, LatMax);
            longitude = RandomUtil.randomDoubleInRange(LongMin, LongMax);
            Pokemon p1 = pokemons.get("L").get(sorteio);
            Aparecimento ap = new Aparecimento();
            ap.setLatitude(latitude);
            ap.setLongitude(longitude);
            ap.setPokemon(p1);
            aparecimentos[contAp] = ap;
            contAp++;
        }
        while (contAp < 4){
            sorteio = RandomUtil.randomIntInRange(0, tamIncomum - 1);
            latitude = RandomUtil.randomDoubleInRange(LatMin, LatMax);
            longitude = RandomUtil.randomDoubleInRange(LongMin, LongMax);
            Pokemon p1 = pokemons.get("I").get(sorteio);
            Aparecimento ap = new Aparecimento();
            ap.setLatitude(latitude);
            ap.setLongitude(longitude);
            ap.setPokemon(p1);
            aparecimentos[contAp] = ap;
            contAp++;
        }
        while(contAp < 10){
            sorteio = RandomUtil.randomIntInRange(0, tamComum - 1);
            latitude = RandomUtil.randomDoubleInRange(LatMin, LatMax);
            longitude = RandomUtil.randomDoubleInRange(LongMin, LongMax);
            Pokemon p1 = pokemons.get("C").get(sorteio);
            Aparecimento ap = new Aparecimento();
            ap.setLatitude(latitude);
            ap.setLongitude(longitude);
            ap.setPokemon(p1);
            aparecimentos[contAp] = ap;
            contAp++;
        }


    }
    
    public boolean loginUser(String login, String senha){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("usuario", new String[] {"login"}, "login = '" + login + "' AND senha = '" + senha + "'", "");

        if(c.getCount() == 1){
            ContentValues valores = new ContentValues();
            valores.put("temSessao", "false");
            bd.atualizar("usuario", valores, "login = '" + login + "'");
            daoUsuario();
            return true;
        }
        return false;
    }
    
    public boolean logoutUser(){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        ContentValues valores = new ContentValues();
        valores.put("temSessao", "true");
        if(bd.atualizar("usuario", valores, "login ='" + user.getLogin() + "'") != -1)
            return true;
        return false;
    }
    
    public boolean cadastrarUser(String login, String nome, String senha, String sexo, String foto){

        Map<String, String> timestamp = TimeUtil.getHoraMinutoSegundoDiaMesAno();
        String data = new String();
        for (String d:timestamp.keySet()) {
            data = d + " " + timestamp.get(d);;
        }
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        //consistente com a versao offline
        bd.deletar("pokemonusuario","");
        bd.deletar("usuario", "");

        Cursor c = bd.buscar("usuario", new String[] {"login"}, "login = '" + login + "'", "");

        if (c.getCount() == 1){
            c.close();
            return false;
        }
        else{
            c.close();
            ContentValues valores = new ContentValues();
            valores.put("login", login);
            valores.put("senha", senha);
            valores.put("nome", nome);
            valores.put("sexo", sexo);
           // valores.put("foto", foto);
            valores.put("dtCadastro", data);
            valores.put("temSessao", "false");

            bd.inserir("usuario", valores);
            daoUsuario();
            return true;
        }
    }

    public boolean temSessao(){
        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();
        Cursor c = bd.buscar("usuario", new String [] {"login"}, "temSessao = 'true'", "");
        if(c.getCount() == 1){
            daoUsuario();
            return true;
        }
        return false;
    }
}
