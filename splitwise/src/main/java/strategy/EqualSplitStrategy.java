package strategy;

public class EqualSplitStrategy implements SplitStrategy{
    private double totalAmount;
    private int numParticipants;
    private double[] values;

    public EqualSplitStrategy(double totalAmount, int numParticipants, double[] values) {
        this.totalAmount = totalAmount;
        this.numParticipants = numParticipants;
        this.values = values;
    }


    private boolean validate() {
        return true;
    }

    @Override
    public double[] getSplitAmount() {
        if(!validate()){
            return null;
        }
        double[] splitAmount = new double[numParticipants];
        double amountPerUser = totalAmount/numParticipants;
        for (int i=0; i<numParticipants; i++){
            splitAmount[i] = amountPerUser;
        }
        if(amountPerUser * numParticipants != totalAmount){
            splitAmount[0] = totalAmount - (amountPerUser * (numParticipants-1));
        }
        return splitAmount;
    }
}
