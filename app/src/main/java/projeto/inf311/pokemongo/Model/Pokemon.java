package projeto.inf311.pokemongo.Model;

import android.database.Cursor;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.inf311.pokemongo.Util.BancoDadosSingleton;

/**
 * Created by vanessa on 04/05/17.
 */

public class Pokemon implements Serializable {
    private int numero;
    private String nome;
    private String categoria;
    private int foto;
    private int icone;
    private List<Tipo> tipos = new ArrayList<>() ;

    public Pokemon() { }

    protected Pokemon(int numero, String nome, String categoria, int foto, int icone, ControladoraFachadaSingleton cg) {
        this.numero = numero;
        this.nome = nome;
        this.categoria = categoria;
        this.foto = foto;
        this.icone = icone;
        preencherTipos(cg);
    }

    private void preencherTipos(ControladoraFachadaSingleton cg){

        BancoDadosSingleton bd = BancoDadosSingleton.getINSTANCE();

        Cursor c = bd.buscar("pokemon p, pokemontipo pt, tipo t ", new String[] {"t.idTipo id"},"pt.idTipo = t.idTipo AND p.idPokemon = pt.idPokemon AND pt.idPokemon = " + numero,"");

        List<Tipo> listTipos = cg.getTiposPokemon();//acho que eh isso

        while (c.moveToNext()){
            for (Tipo t: listTipos) {
                int idTipoBD = c.getInt(c.getColumnIndex("id"));
                if(t.getIdTipo() == idTipoBD){
                    tipos.add(t);
                }
            }
        }
        c.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return numero == pokemon.numero;
    }

    @Override
    public int hashCode() {
        return numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

}
