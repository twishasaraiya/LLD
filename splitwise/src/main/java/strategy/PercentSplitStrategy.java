package strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplitStrategy implements SplitStrategy{
    private List<Long> splitBetweenUserIds;
    private List<Double> percentSplit;
    private Double amount;

    public PercentSplitStrategy(Double amount, List<Long> splitBetweenUserIds, List<Double> percentSplit){
        this.amount = amount;
        this.splitBetweenUserIds = splitBetweenUserIds;
        this.percentSplit = percentSplit;
    }

    @Override
    public Map<Long, Double> split() {
        Map<Long, Double> userToAmountMap = new HashMap<>();
        for (int i = 0; i < splitBetweenUserIds.size(); i++) {
            Double amountForEachUser = ((percentSplit.get(i) / 100) * amount);
            userToAmountMap.put(splitBetweenUserIds.get(i), amountForEachUser);
        }
        return userToAmountMap;
    }
}
