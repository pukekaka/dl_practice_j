package dogtest.booktest.ch2;

import dogtest.booktest.ch2.SingleLayerPerceptron.Perceptrons;
import dogtest.booktest.ch2.SingleLayerPerceptron.util.GaussianDistribution;

import java.util.Random;

public class ch2_1 {
    public static void main(String[] args){
        final int train_N = 1000;
        final int test_N = 200;
        final int nIn = 2;

        double[][] train_X = new double[train_N][nIn];
        int[] train_T = new int[train_N];

        double[][] test_X = new double[test_N][nIn];
        int[] test_T = new int[test_N];
        int[] predicted_T = new int[test_N];

        final int epochs = 2000;
        final double learningRate = 1.;

        final Random rng = new Random(1234);
        GaussianDistribution g1 = new GaussianDistribution(-2.0, 1.0, rng);
        GaussianDistribution g2 = new GaussianDistribution(2.0, 1.0, rng);

        for (int i = 0; i < train_N/2 - 1; i++) {
            train_X[i][0] = g1.random();
            train_X[i][1] = g2.random();
            train_T[i] = 1;
        }
        for (int i = 0; i < test_N/2 - 1; i++) {
            test_X[i][0] = g1.random();
            test_X[i][1] = g2.random();
            test_T[i] = 1;
        }

        // data set in class 2
        for (int i = train_N/2; i < train_N; i++) {
            train_X[i][0] = g2.random();
            train_X[i][1] = g1.random();
            train_T[i] = -1;
        }
        for (int i = test_N/2; i < test_N; i++) {
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

            if (classified_ == train_N)
                break;

            epoch++;
            if (epoch > epochs)
                break;

        }

        for (int i =0; i< test_N; i++){
            predicted_T[i] = classifier.predict(test_X[i]);
        }

        int[][] confusionMatrix = new int[2][2];
        double accuracy = 0.;
        double precision = 0.;
        double recall = 0.;

        for (int i=0; i<test_N; i++){
            if (predicted_T[i] > 0){
                if(test_T[i] >0){
                    accuracy += 1;
                    precision += 1;
                    recall += 1;
                    confusionMatrix[0][0] += 1;
                }else {
                    confusionMatrix[1][0] += 1;
                }
            } else {
                if (test_T[i] > 0){
                    confusionMatrix[0][1] += 1;
                } else {
                    accuracy += 1;
                    confusionMatrix[1][1] += 1;
                }

            }
        }

        accuracy /= test_N;
        precision /= confusionMatrix[0][0] + confusionMatrix[1][0];
        recall /= confusionMatrix[0][0] + confusionMatrix[0][1];

        System.out.println("----------------------------");
        System.out.println("Perceptrons model evaluation");
        System.out.println("----------------------------");
        System.out.printf("Accuracy:  %.1f %%\n", accuracy * 100);
        System.out.printf("Precision: %.1f %%\n", precision * 100);
        System.out.printf("Recall:    %.1f %%\n", recall * 100);

    }

}
