import java.util.HashMap;
import java.util.Map;

public class HowMapWorks {

    public static void main(String... args) {
        /**
         * When you create a HashMap, there are a lot of attributes involve in this.
         * But the most important are:
         * Table; this table will hold all Nodes for the HashMap, it has a initial capacity of
         * 16 rows/elements and this capacity will grow up according to the elements inserted on the hashmap
         * , basically the threshold attribute will mark when it has to grow up.
         * For example if the initial capacity of the hashmap is 16 and the threshold is equal to 12 when
         * the Table reach 12 rows full then the size of the table will grow up to 32 rows
         * Node; Each node contains follow attributes:
         *  key
         *  hascode of the key
         *  value
         *  pointer to the next element; this pointer only will be not null if there is a hashcode collition
         */
        Map<String, Integer> m = new HashMap<>();
        String a = "FB";
        String b = "Ea";
        System.out.println(String.format("%d == %d %b", a.hashCode(), b.hashCode(), b.hashCode() == a.hashCode()));
        m.put(a, 1);
        m.put(b, 2);
        /**
         * As b has the same hash code of a
         * this will add over the same row of the table
         * but there will be two Nodes asociate to this row
         * ; the first Node will be {key : FB, hashcode : calculated, value: 1, next : bNode}
         * , the second Node (bNode) will be {key: Ea,  hascode : same as first Node, value : 2, next : null}
         *
         * So to conclude there is not limite to add same hash codes to the hash map, but there is a restriction
         * for add same keys as we will see on follow puts call
         */
        m.put("cc", 3);
        m.put("cc", 4);
        /**
         * If you add the same key to the HasMap it will only storage the last value for that key
         */
        m.put(null, 5);
        m.put(null, 6);
        System.out.println(m);
    }

}