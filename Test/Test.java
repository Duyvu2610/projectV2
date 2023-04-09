package Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Location, Integer> m = new LinkedHashMap<Location, Integer>();
        Location l1 = new Location(1,1);
        m.put(l1, 1);
        l1.setX(0);

        Map<Location, Integer> m2 = new LinkedHashMap<Location, Integer>(m);
       

        m2.remove(l1);
        m = m2;
        System.out.println(m);
    }
}
