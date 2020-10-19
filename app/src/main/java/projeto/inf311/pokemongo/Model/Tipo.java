package projeto.inf311.pokemongo.Model;

import java.io.Serializable;

/**
 * Created by vanessa on 04/05/17.
 */

public class Tipo implements Serializable {
    private int idTipo;
    private String nome;

    public Tipo(){ }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
