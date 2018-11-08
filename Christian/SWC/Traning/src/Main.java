import java.util.*;

public class Main {

    public static void main(String[] args) {
        int i = 10;
        int count = 0;
        ArrayList<Integer> navn = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            navn.add(i += i);
            count++;
        }
        System.out.print(navn + "Sidste omgang = " + count);
    }
}

