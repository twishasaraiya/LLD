package strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy{

    private final Double amount;
    private final List<Long> splitBetweenUserIds;
    private final List<Double> splitAmountList;

    public ExactSplitStrategy(Double amount, List<Long> splitBetweenUserIds, List<Double> splitAmountList){
        this.amount = amount;
        this.splitBetweenUserIds = splitBetweenUserIds;
        this.splitAmountList = splitAmountList;
    }

    private Boolean validate(){
        Double sum = splitAmountList.stream().reduce(0D, Double::sum);
       return sum.equals(amount);
    }

    @Override
    public Map<Long, Double> split() {

        if(validate().equals(Boolean.FALSE)){
            throw new IllegalStateException("Sum of shares not equal to the total sum");
        }
        if(splitBetweenUserIds.size() != splitAmountList.size()){
            System.out.println("split strategy not set properly...Cannot split invalid data");
            return Collections.emptyMap(); // should an error be thrown here
        }
        HashMap<Long, Double> map = new HashMap<>();
        for (int i = 0; i < splitBetweenUserIds.size(); i++) {
            map.put(splitBetweenUserIds.get(i), splitAmountList.get(i));
        }
        return map;
    }
}
