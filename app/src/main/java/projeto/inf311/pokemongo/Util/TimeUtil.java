package projeto.inf311.pokemongo.Util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vanessa on 04/05/17.
 */

public class TimeUtil {
    public static Map<String, String> getHoraMinutoSegundoDiaMesAno(){
        Calendar data = Calendar.getInstance();
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int min = data.get(Calendar.MINUTE);
        int seg = data.get(Calendar.SECOND);
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH);
        int ano = data.get(Calendar.YEAR);

        String dataAtual = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + ano;
        String horaAtual = String.format("%02d", hora) + ":" + String.format("%02d", min) + ":" + String.format("%02d", seg);

        Map<String, String> aux = new HashMap<>();
        aux.put(dataAtual, horaAtual);
        return aux;
    }


}
