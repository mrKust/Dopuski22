import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {


        double nModeling = 0;
        int numberOfExperiments = 10000;
        int finalPow = 10;
        int startHead = 0;
        int finalHead = 1;


        MarkovChain chain = new MarkovChain();
        chain.addHeadToChain(0, Arrays.asList(0.7, 0.3));
        chain.addHeadToChain(1, Arrays.asList(0.1, 0.9));
        /*chain.addHeadToChain(0, Arrays.asList(0.5, 0.5));
        chain.addHeadToChain(1, Arrays.asList(0.5, 0.5));*/

        FileWork file = new FileWork("D:\\Pereezd\\Labs\\Тюрликов\\model.txt", false);

        for (int i = 1; i <= finalPow; i++) {

            int nSuccessful = 0;
            int nSummary = 0;

            while (nSummary < numberOfExperiments) {

                int finalPosition = chain.processInTime(startHead, i);

                if (finalPosition == finalHead)
                    nSuccessful++;

                nSummary++;
            }

            nModeling = (double) nSuccessful / nSummary;
            System.out.println("N by modeling " + nModeling);

            double[][] MarkovChain = {{0.7, 0.3}, {0.1, 0.9}};
            //double[][] MarkovChain = {{0.5, 0.5}, {0.5, 0.5}};
            double[] Po = {1, 0};

            double[][] matrixInPow = Matrix.power(MarkovChain, i);
            double[][] resultVector = Matrix.increase(matrixInPow, Po);
            System.out.println("N by computation " + Arrays.toString(resultVector[0]));

            file.write(i, nModeling, resultVector[0][1]);
        }

    }


}
