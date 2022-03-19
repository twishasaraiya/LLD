package strategy;

public class PercentageSplitStrategy implements SplitStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public PercentageSplitStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }

    private boolean validate() {
        if(values.length != numParticipants){
            return false;
        }
        double percentageSum = 0.0;
        for(double value: values){
            percentageSum += value;
        }
        return percentageSum == 100.0;
    }

    @Override
    public double[] getSplitAmount() {
        if(!validate()){
            return null;
        }
        double[] splitAmount = new double[numParticipants];
        double userAmount;
        for (int i=0; i<numParticipants; i++){
            userAmount = (totalAmount * values[i])/100;
            splitAmount[i] = userAmount;
        }
        return splitAmount;
    }
}
