import java.util.*;

public class MyArrayList<T> implements MyList {

    private T[] data = (T[]) new Object[5];
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object element) {
        if (data.length - size < 1) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = (T) element;
    }

    @Override
    public void remove(int index) {
            while (index < size){
                data[index] = data[index + 1];
                index++;
            }

    size--;
    }

    @Override
    public Object get(int index) {
        return data[index];
    }
}
