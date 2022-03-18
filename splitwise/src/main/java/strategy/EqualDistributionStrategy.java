package strategy;

public class EqualDistributionStrategy implements IDistributionStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public EqualDistributionStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }

    @Override
    public double[] getAmountDistribution() {
        double[] amountDistribution = new double[numParticipants];
        double amountPerUser = totalAmount/numParticipants;
        double assignedAmount = 0.0;
        for (int i=0; i<numParticipants; i++){
            amountDistribution[i] = amountPerUser;
            assignedAmount += amountPerUser;
        }
        if(assignedAmount != totalAmount){
            amountDistribution[0] = totalAmount - (amountPerUser * (numParticipants-1));
        }
        return amountDistribution;
    }
}
