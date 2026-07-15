package java.src.splitwise.str;

import java.src.splitwise.models.Split;
import java.util.List;

public interface SplitStrategy {

    List<Split> calcSplit(double TotalAmt, List<String> users, List<Double> values);
} 