import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        int sizeOfBuffer = 10;
        double T = 1_000_000;

        FileWork file = new FileWork("D:\\Pereezd\\Labs\\Тюрликов\\model4.txt", false);

        for (double lambda = 0.1; lambda < 2.0; lambda += 0.1) {
            System.out.println("lambda = " + lambda);
            double[] modelingResult = Dopusk4.modeling(T, lambda, sizeOfBuffer);
            double[] theorResult = Dopusk4.theor(lambda, sizeOfBuffer);
            System.out.println("------------------------------------");
            file.write(lambda, modelingResult, theorResult);
        }

    }
}
