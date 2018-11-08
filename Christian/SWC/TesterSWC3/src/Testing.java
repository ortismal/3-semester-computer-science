
import java.util.*;
public class Testing {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        names.add("alice");
        names.add("bob");
        names.add("david");

                if (names.contains("bob")) {
                    names.add("char");
                }
        ArrayList<String> tempList = new ArrayList<>();
        names.forEach(E->{
            System.out.println(E);
            String temp = E.toUpperCase();
            tempList.add(temp);
        });
        System.out.println(names);
    }
}
