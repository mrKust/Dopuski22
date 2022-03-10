import java.util.Arrays;

public class Matrix {

    public static double[][] powerHuge(double[][] matrix, int n) {
        double[][] outputMinusOne = matrix;
        double[][] output = matrix;
        for (int i = 2; i <= n; i++) {
            outputMinusOne = output;
            output = Matrix.increase(output, matrix);
            if (Arrays.deepEquals(outputMinusOne, output))
                break;

        }
        return outputMinusOne;
    }

    public static double[][] power(double[][] matrix, int n) {
        double[][] output = matrix;
        for (int i = 2; i <= n; i++) {
            output = Matrix.increase(output, matrix);
        }
        return output;
    }

    public static double[][] increase(double[][] matrixA, double[][] matrixB) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        int o = matrixB.length;
        double[][] output = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    output[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return output;
    }

    public static double[][] increase(double[][] matrixA, double[] vector) {

        for (int i = 0; i < matrixA[0].length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixA[j][i] *= vector[j];
            }
        }

        return matrixA;
    }

}
