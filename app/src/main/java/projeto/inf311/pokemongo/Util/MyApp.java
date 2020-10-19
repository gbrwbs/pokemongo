package projeto.inf311.pokemongo.Util;

import android.app.Application;
import android.content.Context;

/**
 * Created by vanessa on 04/05/17.
 */

public class MyApp extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MyApp.context;
    }
}
