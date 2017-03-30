package org.dog.perceptrons;

/**
 * Created by pk on 2017-03-30.
 */
public final class Activationfunction {

    public static int step(double x){
        if (x>=0){
            return 1;
        }else {
            return -1;
        }
    }
}
