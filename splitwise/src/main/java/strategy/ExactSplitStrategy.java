package strategy;

public class ExactSplitStrategy implements SplitStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public ExactSplitStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }


    private boolean validate() {
        if(values.length != numParticipants){
            return false;
        }
        double amountSum = 0.0;
        for(double value: values){
            amountSum += value;
        }
        return amountSum == totalAmount;
    }

    @Override
    public double[] getSplitAmount() {
        if(!validate()){
            return null;
        }
        return values;
    }
}
