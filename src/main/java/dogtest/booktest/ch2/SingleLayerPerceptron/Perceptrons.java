package dogtest.booktest.ch2.SingleLayerPerceptron;

import static dogtest.booktest.ch2.SingleLayerPerceptron.util.ActivationFunction.step;

public class Perceptrons {
    public int nIn;
    public double[] w;

    public Perceptrons(int nIn){
        this.nIn = nIn;
        w = new double[nIn];
    }

    public int train(double[] x, int t, double learningRate){
        int classified = 0;
        double c = 0.;

        for (int i =0; i< nIn; i++){
            c += w[i] * x[i] *t;
        }

        if (c > 0){
            classified = 1;
        } else {
            for (int i = 0; i < nIn; i++){
                w[i] += learningRate * x[i] * t;
            }
        }

        return classified;
    }

    public int predict (double[] x){
        double preActivation = 0.;

        for (int i =0; i< nIn; i++){
            preActivation += w[i] * x[i];
        }

        return step(preActivation);
    }
}
