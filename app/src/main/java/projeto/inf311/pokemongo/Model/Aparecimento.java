package projeto.inf311.pokemongo.Model;

import java.io.Serializable;

/**
 * Created by vanessa on 04/05/17.
 */

public class Aparecimento implements Serializable {
    private double latitude;
    private double longitude;
    private Pokemon pokemon;

    public Aparecimento() { }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}

