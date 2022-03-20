package strategy;

import java.util.Map;

public interface SplitStrategy {
    Map<Long, Double> split();
}
