package projeto.inf311.pokemongo.Util;

import java.util.Random;

/**
 * Created by vanessa on 04/05/17.
 */

public class RandomUtil {
    public static double randomDoubleInRange(double min, double max){
        Random rand = new Random();
        return (min + (max - min) * rand.nextDouble());
    }

    public static int randomIntInRange(int min, int max){
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min);
    }

}
