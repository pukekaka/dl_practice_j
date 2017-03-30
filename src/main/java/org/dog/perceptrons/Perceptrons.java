package org.dog.perceptrons;

import java.util.Random;
import org.math.plot.*;

import javax.swing.*;

import static org.dog.perceptrons.Activationfunction.step;

/**
 * Created by pk on 2017-03-30.
 */
public class Perceptrons {

    public int nIn;
    public double[] w;

    public Perceptrons(int nln){

        this.nIn = nln;
        w = new double[nln];
    }

    public int train(double[] x, int t, double learningRate){

        int classified = 0;
        double c = 0;

        for (int i = 0; i < nIn; i++){
            c += w[i] * x[i] * t;
            System.out.println("i: "+i+", c: "+c);
        }

        if (c > 0){
            classified = 1;
        } else {
            for (int i = 0; i< nIn; i++){
                w[i] += learningRate * x[i] * t;
            }
        }

        return classified;
    }

    public int predict (double[] x){

        double preActivation = 0;

        for (int i=0; i< nIn; i++){
            preActivation += w[i] * x[i];
        }

        return step(preActivation);
    }

    public static void main(String[] args){

        final int train_N = 1000;
        final int test_N = 200;
        final int nIn = 2;

        double[][] train_X = new double[train_N][nIn];
        int[] train_T = new int[train_N];

        double[][] test_X = new double[test_N][nIn];
        int[] test_T = new int[train_N];
        int[] predicted_T = new int[test_N];

        final int epochs = 2000;
        final double learningRate = 1.;


        //create data
        final Random rng = new Random(1234);
        GaussianDistribution g1 = new GaussianDistribution(-2.0, 1.0, rng);
        GaussianDistribution g2 = new GaussianDistribution(2.0, 1.0, rng);

        for (int i =0; i< train_N/2 -1; i++){
            train_X[i][0] = g1.random();
            train_X[i][1] = g2.random();
            train_T[i] = 1;
        }
        for (int i=0; i< test_N/2 -1; i++){
            test_X[i][0] = g1.random();
            test_X[i][1] = g2.random();
            test_T[i] = 1;
        }

        for (int i = train_N/2; i < train_N; i++){
            train_X[i][0] = g2.random();
            train_X[i][1] = g1.random();
            train_T[i] = -1;
        }

        for (int i = test_N/2; i<test_N; i++){
            test_X[i][0] = g2.random();
            test_X[i][1] = g1.random();
            test_T[i] = -1;
        }

        int epoch = 0;

        Perceptrons classifier = new Perceptrons(nIn);

        while (true){
            int classified_ = 0;

            for (int i=0; i<train_N; i++){
                classified_ += classifier.train(train_X[i], train_T[i], learningRate);
            }

            if (classified_ == train_N) break;

            epoch ++;

            if(epoch > epochs) break;
        }

        for (int i =0; i< test_N; i++){
            predicted_T[i] = classifier.predict(test_X[i]);
        }

        int[][] confusionMatrix = new int[2][2];
        double accuracy = 0.;
        double precision = 0.;
        double recall = 0.;

        for(int i=0; i<test_N; i++){
            if(predicted_T[i]>0){
                if(test_T[i]>0){
                    accuracy += 1;
                    precision += 1;
                    recall+= 1;
                    confusionMatrix[0][0] += 1;
                }else {
                    confusionMatrix[1][0] += 1;
                }
            } else {
                if(test_T[i] > 0){
                    confusionMatrix[0][1] += 1;
                }else {
                    accuracy += 1;
                    confusionMatrix[1][1] += 1;
                }
            }
        }

        accuracy /=test_N;
        precision /= confusionMatrix[0][0] + confusionMatrix[1][0];
        recall /= confusionMatrix[0][0] + confusionMatrix[0][1];


/*
        for(int i=0; i<train_X.length; i++){

            for (int j=0; j<2; j++){
                System.out.print("["+i+"]"+"["+j+"]"+"=>" + train_X[i][j]+"  ");

            }

            System.out.println();
        }

        double[] x = new double[1000];
        double[] y = new double[1000];

        for (int i=0; i< train_X.length; i++){
            x[i] = (train_X[i][0]);
            y[i] = (train_X[i][1]);
        }

        Plot2DPanel plot = new Plot2DPanel();

        plot.addScatterPlot("gaussianDistribution Data", x, y);

        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setVisible(true);

*/

        System.out.println("----------------------------");
        System.out.println("Perceptrons model evaluation");
        System.out.println("----------------------------");
        System.out.printf("Accuracy:  %.1f %%\n", accuracy * 100);
        System.out.printf("Precision: %.1f %%\n", precision * 100);
        System.out.printf("Recall:    %.1f %%\n", recall * 100);

    }

}
