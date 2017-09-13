package org.dog.test;

/**
 * Created by pukekakaster on 10/05/2017.
 */

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Temp {
    public static void main(String args[]){

        INDArray x = Nd4j.create(new float[]{0,1});
        INDArray w = Nd4j.create(new double[]{0.5,0.5});
        double b = -0.7;

        System.out.println(Nd4j.sum(x.mul(w)).add(b));

        //INDArray nd = Nd4j.create(new float[]{1,2},new int[]{2}); //row vector
        //INDArray nd2 = Nd4j.create(new float[]{3,4},new int[]{2, 1}); //column vector



    }

}
