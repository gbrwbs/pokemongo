package projeto.inf311.pokemongo.Util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by vanessa on 04/05/17.
 */

public class ViewUnitsUtil {
    public float convertDpToPixel(float dp){
        Context ctx = MyApp.getAppContext();

        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
