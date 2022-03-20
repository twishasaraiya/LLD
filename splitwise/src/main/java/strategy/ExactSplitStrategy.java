package strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplitStrategy implements SplitStrategy{

    private List<Long> splitBetweenUserIds;
    private List<Double> splitAmount;

    public ExactSplitStrategy(List<Long> splitBetweenUserIds, List<Double> splitAmount){
        this.splitBetweenUserIds = splitBetweenUserIds;
        this.splitAmount = splitAmount;
    }

    @Override
    public Map<Long, Double> split() {
        if(splitBetweenUserIds.size() != splitAmount.size()){
            System.out.println("split strategy not set properly...Cannot split invalid data");
            return Collections.emptyMap(); // should an error be thrown here
        }
        HashMap<Long, Double> map = new HashMap<>();
        for (int i = 0; i < splitBetweenUserIds.size(); i++) {
            map.put(splitBetweenUserIds.get(i), splitAmount.get(i));
        }
        return map;
    }
}
