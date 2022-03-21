package strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplitStrategy implements SplitStrategy{
    private final List<Long> splitBetweenUserIds;
    private final List<Double> percentSplit;
    private final Double amount;
    private static final Double TOTAL_PERCENT_SUM = 100D;

    public PercentSplitStrategy(Double amount, List<Long> splitBetweenUserIds, List<Double> percentSplit){
        this.amount = amount;
        this.splitBetweenUserIds = splitBetweenUserIds;
        this.percentSplit = percentSplit;
    }

    private Boolean validate(){
        Double sum = percentSplit.stream().reduce(0D, Double::sum);
return sum.equals(TOTAL_PERCENT_SUM);
    }

    @Override
    public Map<Long, Double> split() {
        if (validate().equals(Boolean.FALSE)) {
            throw new IllegalStateException("Sum of shares not equal to total sum");
        }
        Map<Long, Double> userToAmountMap = new HashMap<>();
        for (int i = 0; i < splitBetweenUserIds.size(); i++) {
            Double amountForEachUser = ((percentSplit.get(i) / 100) * amount);
            userToAmountMap.put(splitBetweenUserIds.get(i), amountForEachUser);
        }
        return userToAmountMap;
    }
}
