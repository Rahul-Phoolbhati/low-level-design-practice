package java.src.splitwise.str;

import java.src.splitwise.models.Split;
import java.util.ArrayList;
import java.util.List;

public class EqualSplit implements SplitStrategy {
    @Override
    public List<Split> calcSplit(double TotalAmt, List<String> users, List<Double> values) {

        List<Split> splits = new ArrayList<>();
        double pay = TotalAmt/users.size();

        for(String u : users){
            splits.add(new Split(u, pay));
        }

        return splits;
    }
}
