package atm.respchain;

import java.util.Map;

public class DispensorChainBuilder {
    public static CardDispensor build(Map<Integer, Integer> inventory) {
        CardDispensor d100 = new NoteDispensor100(inventory.getOrDefault(100, 0));
        CardDispensor d200 = new NoteDispensor200(inventory.getOrDefault(200, 0), d100);
        CardDispensor d500 = new NoteDispensor500(inventory.getOrDefault(500, 0), d200);

        return d500;
    }
}
