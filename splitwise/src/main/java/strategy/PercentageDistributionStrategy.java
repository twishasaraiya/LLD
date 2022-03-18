package strategy;

public class PercentageDistributionStrategy implements IDistributionStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public PercentageDistributionStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }

    @Override
    public double[] getAmountDistribution() {
        double[] amountDistribution = new double[numParticipants];
        double userAmount;
        for (int i=0; i<numParticipants; i++){
            userAmount = (totalAmount * values[i])/100;
            amountDistribution[i] = userAmount;
        }
        return amountDistribution;
    }
}
