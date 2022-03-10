import java.util.Arrays;

public class Experiment {

    public static void main(int start) {

        int numberOfExperiments = 100000;
        int startHead = start;

        MarkovChain chain = new MarkovChain();

        chain.addHeadToChain(0, Arrays.asList(0.9, 0.07, 0.03));
        chain.addHeadToChain(1, Arrays.asList(0.8, 0.1, 0.1));
        chain.addHeadToChain(2, Arrays.asList(0.0, 0.0, 1.0));

        double[][] MarkovChain = {{0.9, 0.07, 0.03}, {0.8, 0.1, 0.1}, {0.0, 0.0, 1.0}};

        /*chain.addHeadToChain(0, Arrays.asList(0.5, 0.5));
        chain.addHeadToChain(1, Arrays.asList(0.5, 0.5));
        double[][] MarkovChain = {{0.5, 0.5}, {0.5, 0.5}};*/

        FileWork file = new FileWork("D:\\Pereezd\\Labs\\Тюрликов\\modelDopusk3.txt", false);

        double summaryTimeToN = 0.0;

        for (int i = 0; i < numberOfExperiments; i++) {
            int timeToN = chain.timeToN(startHead);
            summaryTimeToN += timeToN;
        }

        double FinModeling = summaryTimeToN / numberOfExperiments;
        System.out.println("Среднее время перехода в поглощающее состояние (моделирование) = " + FinModeling);



        double[][] matrixPshtrih = Matrix.shrinkMatrix(MarkovChain, MarkovChain[0].length - 1);
        double[][] matrixEdin = Matrix.createEdinMatrix(matrixPshtrih.length);
        double[][] matrixA = Matrix.subtractMatrix(matrixEdin, matrixPshtrih);
        double[] Po = Matrix.createEdinVector(matrixA.length);
        double[][] matrixAInverse = Matrix.inversionMatrix(matrixA);
        double[] vectorResult = Matrix.increase(matrixAInverse, Po);

        System.out.println("Среднее время перехода в поглощающее состояние (формула) = " + vectorResult[startHead]);

    }
}
