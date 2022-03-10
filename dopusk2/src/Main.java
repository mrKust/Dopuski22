import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double nModeling = 0;
        int lengthOfExperiment = 10000000;
        int startHead = 0;
        int finalHead = 1;



        MarkovChain chain = new MarkovChain();

        chain.addHeadToChain(0, Arrays.asList(0.7, 0.3));
        chain.addHeadToChain(1, Arrays.asList(0.1, 0.9));
        double[][] MarkovChain = {{0.7, 0.3}, {0.1, 0.9}};

        /*chain.addHeadToChain(0, Arrays.asList(0.5, 0.5));
        chain.addHeadToChain(1, Arrays.asList(0.5, 0.5));
        double[][] MarkovChain = {{0.5, 0.5}, {0.5, 0.5}};*/

        FileWork file = new FileWork("D:\\Pereezd\\Labs\\Тюрликов\\modelDopusk2.txt", false);

        int[] headsCount = chain.processInTimeAndCountHeads(startHead, lengthOfExperiment);
        double[] pModeling = new double[headsCount.length];

        for (int i = 0; i < pModeling.length; i++) {
            pModeling[i] = (double) headsCount[i] / lengthOfExperiment;
            System.out.println("Статистическая вероятность p" + i + " = " + pModeling[i]);
        }


        double[] Po = {1, 0};

        double[][] matrixInPow = Matrix.powerHuge(MarkovChain, lengthOfExperiment);
        double[][] resultVector = Matrix.increase(matrixInPow, Po);
        System.out.println("N by computation " + Arrays.toString(resultVector[0]));



    }

}
