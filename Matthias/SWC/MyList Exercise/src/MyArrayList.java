import java.util.Arrays;

public class MyArrayList<T> implements MyList {

    private T[] data = (T[]) new Object[0];
    private int size = 0;

    @Override
    public int size(){
        return size;
    }

    @Override
    public Object get(int x){
        return data[x];
    }

    @Override
    public void add(Object x){
        if(data.length - size < 1) {
            data = Arrays.copyOf(data, data.length + 10);
        }
        data[size++] = (T) x;
    }

    @Override
    public void remove(int index){
        while(index < size){
            data[index] = data[index+1];
            index++;
        }
        size--;
        //data = Arrays.copyOf(data, size);
    }

}
