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

    public static double[] increase(double[][] matrixA, double[] vector) {

        double[] resultVector = new double[matrixA.length];
        for (int j = 0; j < resultVector.length; j++) {
            for (int i = 0; i < matrixA.length; i++) {
                resultVector[j] += matrixA[j][i] * vector[i];
            }
        }

        return resultVector;
    }

    public static double[][] shrinkMatrix(double[][] matrixA, int sizeToShrink) {
        double[][] result = new double[sizeToShrink][sizeToShrink];

        for (int i = 0; i < sizeToShrink; i++) {
            for (int k = 0; k < sizeToShrink; k++) {
                result[i][k] = matrixA[i][k];
            }
        }
        return result;
    }

    public static double[][] subtractMatrix(double[][] matrixA, double[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length)
            System.err.println("Matrix can not be subtract because of different sizes");
        else {
            double[][] result = new double[matrixA.length][matrixB[0].length];
            for (int i = 0; i < result[0].length; i++) {
                for(int k = 0; k < result.length; k++){
                    result[i][k] = matrixA[i][k] - matrixB[i][k];
                }
            }
            return result;
        }
        return null;
    }

    public static double[][] createEdinMatrix(int size) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (i == k)
                    result[i][k] = 1.0;
            }
        }
        return result;
    }

    public static double[] createEdinVector(int size) {
        double[] result = new double[size];
        for (int i = 0; i < size; i++) {
            result[i] = 1.0;
        }

        return result;
    }

    public static double[][] inversionMatrix(double[][] matrixA)
    {
        double temp;

        double[][] result = new double[matrixA.length][matrixA.length];

        for (int i = 0; i < matrixA.length; i++)
            for (int j = 0; j < matrixA.length; j++)
            {
                result[i][j] = 0d;

                if (i == j)
                    result[i][j] = 1d;
            }

        for (int k = 0; k < matrixA.length; k++)
        {
            temp = matrixA[k][k];

            for (int j = 0; j < matrixA.length; j++)
            {
                matrixA[k][j] /= temp;
                result[k][j] /= temp;
            }

            for (int i = k + 1; i < matrixA.length; i++)
            {
                temp = matrixA[i][k];

                for (int j = 0; j < matrixA.length; j++)
                {
                    matrixA[i][j] -= matrixA[k][j] * temp;
                    result[i][j] -= result[k][j] * temp;
                }
            }
        }

        for (int k = matrixA.length - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = matrixA[i][k];

                for (int j = 0; j < matrixA.length; j++)
                {
                    matrixA[i][j] -= matrixA[k][j] * temp;
                    result[i][j] -= result[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < matrixA.length; i++)
            for (int j = 0; j < matrixA.length; j++)
                matrixA[i][j] = result[i][j];

        return result;
    }

}
