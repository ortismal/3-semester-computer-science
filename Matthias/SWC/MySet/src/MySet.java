import java.util.Arrays;

public class MySet<E> implements MyTreeSet {

    private E[] set = (E[]) new Object[0];
    private int size = 0;

    @Override
    public boolean add(Object x) {
        if(set.length - size < 1) {
            set = Arrays.copyOf(set, set.length + 10);
        }
        for (E obj : set) {
            if (x == obj) {
                System.out.println("Element already exists!");
                return false;
            }
        }
        set[size++] = (E) x;
        System.out.println("Element added");
        return true;
    }

    @Override
    public boolean contains(Object x){
        for(E obj : set){
            if(x == obj){
                System.out.println("Element exists in set");
                return true;
            }
        }
        System.out.println("Element doesn't exist in set");
        return false;
    }

    @Override
    public void remove(Object x){
        if(set.length - size > 12) {
            set = Arrays.copyOf(set, set.length - 10);
        }
        int i = 0;
        for(E obj : set) {
            if (obj == x) {
                set[i] = null;
                System.out.println("Element: " + obj.toString() + " has been removed.");
                while (i < size) {
                    set[i] = set[i + 1];
                    i++;
                }
            }
            i++;
        }
        size--;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return "MySet{" +
                "set=" + (set == null ? null : Arrays.asList(set)) +
                ", size=" + size +
                '}';
    }
}
