import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MarkovChain {

    private class Head {
        private int numberInOrder;
        private List<Double> probabilitiesToSwitch;

        public Head(int numberInOrder, List<Double> probabilitiesToSwitch) {
            this.numberInOrder = numberInOrder;
            this.probabilitiesToSwitch = new ArrayList<>(probabilitiesToSwitch);
        }

        public int getNumberInOrder() {
            return numberInOrder;
        }

        public void setNumberInOrder(int numberInOrder) {
            this.numberInOrder = numberInOrder;
        }

        public List<Double> getProbabilitiesToSwitch() {
            return probabilitiesToSwitch;
        }

        public void setProbabilitiesToSwitch(List<Double> probabilitiesToSwitch) {
            this.probabilitiesToSwitch = probabilitiesToSwitch;
        }
    }

    List<Head> chain;

    public MarkovChain() {

        chain = new ArrayList<>();
    }

    public void addHeadToChain(int number, List<Double> probability) {
        Head tmp = new Head(number, probability);
        chain.add(tmp);
    }

    public int processInTime(int startPosition, int T) {

        int finalHead = startPosition;

        for (int i = 0; i < T; i++) {

            List<Double> switchesChances = chain.get(finalHead).getProbabilitiesToSwitch();
            double probabilityToSwitch = Math.random();

            double summaryProbability = 0;
            for (int k = 0; k < switchesChances.size(); k++) {
                if ( (summaryProbability < probabilityToSwitch) &&
                        (probabilityToSwitch < ( summaryProbability + switchesChances.get(k) ) ) ) {
                    finalHead = k;
                    break;
                }
                summaryProbability += switchesChances.get(k);
            }

        }

        return finalHead;
    }


}
