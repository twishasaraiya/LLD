package strategy;

public class ExactDistributionStrategy implements IDistributionStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public ExactDistributionStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }

    @Override
    public double[] getAmountDistribution() {
        return values;
    }
}
