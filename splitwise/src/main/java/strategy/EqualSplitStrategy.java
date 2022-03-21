package strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    private Double amount;
    private List<Long> splitBetweenUserIds;

    public EqualSplitStrategy(Double amount, List<Long> splitBetweenUserIds){
        this.amount = amount;
        this.splitBetweenUserIds = splitBetweenUserIds;
    }


    @Override
    public Map<Long, Double> split() {
        Double amountForEachUser = (double) Math.round((amount / splitBetweenUserIds.size()) * 100) / 100;
        HashMap<Long, Double> userToAmountMap = new HashMap<>();
        for (Long userId:
             splitBetweenUserIds) {
            userToAmountMap.put(userId, amountForEachUser);
        }
        return userToAmountMap;
    }
}
