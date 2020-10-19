package projeto.inf311.pokemongo.Model;

/**
 * Created by vanessa on 04/05/17.
 */

public class PokemonCapturado {
    private double latitude;
    private double longitude;
    private String dtCaptura;

    public PokemonCapturado() { }

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

    public String getDtCaptura() {
        return dtCaptura;
    }

    public void setDtCaptura(String dtCaptura) {
        this.dtCaptura = dtCaptura;
    }
}
