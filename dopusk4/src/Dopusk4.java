import java.util.*;

public class Dopusk4 {

    public static double[] modeling(double T, double lambda, int sizeOfBuffer) {

        List<Double> inputList = new ArrayList<>();
        inputList.add(-(Math.log(Math.random()) / lambda));

        while (inputList.get(inputList.size() - 1) < T) {
            inputList.add(inputList.get(inputList.size() - 1) + (- (Math.log(Math.random()) / lambda)));
        }

        double delay = 0.0;
        double tCurrent = 0.0;
        Deque<Double> queue = new ArrayDeque<Double>();
        int inputInBuffer = 0;
        int numberOfExited = 0;
        int indexOfLastInputUsage = 0;
        double sredNumUsers = 0.0;

        while (tCurrent < T) {

            sredNumUsers += inputInBuffer;

            while (inputList.get(indexOfLastInputUsage) < tCurrent) {
                if (inputInBuffer < sizeOfBuffer) {
                    queue.offer(inputList.get(indexOfLastInputUsage) + 1.0);
                    inputInBuffer++;
                }
                indexOfLastInputUsage++;
            }

            if ( (inputInBuffer != 0) && (queue.getFirst() < tCurrent) ) {
                delay += tCurrent - queue.poll();
                numberOfExited++;
                inputInBuffer--;
            }
            tCurrent++;

        }

        double eNmodeling = sredNumUsers / T;
        System.out.println("Среднее количество заявок в системе (моделирование) E[N]= " + eNmodeling);
        double eDmodeling = delay / numberOfExited;
        System.out.println("Средняя задержка в системе (моделирование) E[D]= " + eDmodeling);
        double eLambdaOutmodeling =  numberOfExited / T;
        System.out.println("Средняя выходная интенсивность в системе (моделирование) lambda_out = " + eLambdaOutmodeling);

        return new double[]{eNmodeling, eDmodeling, eLambdaOutmodeling};
    }

    public static double[] theor(double lambda, int sizeOfBuffer) {
        double eInLambda = Math.pow(Math.E, -lambda);
        double[][] perehodProb = createMatrixPerehodVer(new double[sizeOfBuffer+1][sizeOfBuffer+1],
                lambda, eInLambda, sizeOfBuffer);
        double[][] A = findA(perehodProb);
        Matrix.inversionMatrix(A);
        double[] pi = findPi(A);
        double eNtheor = 0.0;
        for(int i = 0; i < sizeOfBuffer; i++){
            eNtheor += i * pi[i];
        }
        System.out.println("Среднее количество заявок в системе (теоретически) E[N]= " + eNtheor);
        double eDtheor= eNtheor / (1.0 - pi[0]);
        System.out.println("Средняя задержка в системе (теоретически) E[D]= " + eDtheor);
        double eLambdaOutTheor =  1.0 - pi[0];
        System.out.println("Средняя выходная интенсивность в системе (теоретически) lambda_out = " + eLambdaOutTheor);

        return new double[]{eNtheor, eDtheor, eLambdaOutTheor};
    }

    private static double[] findPi(double[][]A){
        double[] B = new double[A.length];
        B[B.length - 1] = 1;
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                result[i] += A[i][j] * B[j];
            }
        }
        return result;
    }

    private static double[][] findA(double[][] matrixIn) {
        double[][] res = new double[matrixIn.length][matrixIn[0].length];
        for (int i = 0; i < matrixIn.length; i++) {
            for (int j = 0; j < matrixIn[i].length; j++) {
                res[i][j] = matrixIn[j][i];
            }
        }
        for (int i = 0; i < res.length; i++) {
            res[i][i] = res[i][i] - 1;
        }
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1][i] = 1;
        }
        return res;
    }

    public static double[][] createMatrixPerehodVer(double[][] matrixIn, double lambda,
                                                    double eInLambda, int sizeOfBuffer) {

        double[][] matrixResult = new double[matrixIn.length - 1][matrixIn[0].length - 1];
        for (int i = 0; i < matrixResult.length; i++) {
            double sum = 0.0;
            if (i == 0) {
                int j = i;
                while (j < matrixResult.length-i-1) {
                    matrixResult[i][j] = (Math.pow(lambda,j)/factorial(j)) * eInLambda;
                    sum += matrixResult[i][j];
                    j++;
                }
                matrixResult[i][j] = 1.0 - sum;
            } else {
                if (i == 1) {
                    matrixResult[i] = inputLine(0, sizeOfBuffer - 1, matrixResult[i-1],
                            0);
                } else {
                    matrixResult[i] = inputLine(i - 1, sizeOfBuffer - 1, matrixResult[i-1],
                            1);
                }
            }

        }
        return matrixResult;
    }

    private static double[] inputLine(int start,int stop,double[] prevLine, int k){
        double[] res = new double[prevLine.length];
        double sum = 0.0;
        int j = start;
        while (j < stop) {
            res[j] = prevLine[j-k];
            sum += res[j];
            j++;
        }
        res[j] = 1 - sum;
        return res;
    }

    private static double factorial(double i){
        if(i <= 1){
            return 1.0;
        }
        return i * factorial(i-1);
    }
}