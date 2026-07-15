package java.src.splitwise.factory;

import java.src.splitwise.eums.SplitType;
import java.src.splitwise.str.EqualSplit;
import java.src.splitwise.str.ExactSplit;
import java.src.splitwise.str.SplitStrategy;

public class SplitFactory {
    public static SplitStrategy getSplitStrategy(SplitType sp){
        switch (sp) {
            case EQUAL:
                return new EqualSplit();

            case EXACT:
                return new ExactSplit();
        
            default:
                return new EqualSplit();
        }
    }
}
