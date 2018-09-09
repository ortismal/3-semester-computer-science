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
        return data[x];
    }

    @Override
    public void add(Object x){
        if(data.length - size < 1) {
            data = Arrays.copyOf(data, data.length + 1);
        }
        data[size++] = (T) x;
    }

    @Override
    public void remove(int index){
        if(index < size){
            data[index] = data[index+1];
        }
        data = Arrays.copyOf(data, data.length-1);
    }

}
