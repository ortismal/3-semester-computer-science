import java.util.Arrays;

public class MyArrayList<T> implements MyList {

    private T[] data = (T[]) new Object[5];
    private int size = 0;

    @Override
    public int size(){
        return data.length;
    }

    @Override
    public Object get(int x){
        return "";
    }

    @Override
    public void add(Object x){
        data = Arrays.copyOf(data, data.length+1);
        data[size++] = (T) x;
    }

    @Override
    public Object remove(int index){

        Object x = data[index];

        return "";
    }

}
