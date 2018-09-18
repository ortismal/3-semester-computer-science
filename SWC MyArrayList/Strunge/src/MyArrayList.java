import java.util.Arrays;

public class MyArrayList<T> implements MyList {

    private T[] data = (T[]) new Object[5];
    private int size = 0;

    @Override
    //Check for current length
    public int size() {
        return size;
    }

    @Override
    //Add to current list
    public void add(Object element) {
        //Check if there is anymore room in the list
        if (data.length - size < 1){
            //Copies old array, and adds its indexes to a new array, which is longer
            data = Arrays.copyOf(data, data.length + 5);
        }
        //Increments size, and sets the element as the same as size, at size 5 it does (5*2) so that Object[5] becomes Object[10]
        data[size++] = (T) element;
    }

    @Override
    public Object remove(int index) {
        //Checks if the index is within the array
        Object obj = null;
        if (index < size) {
            obj = data[index];
            while (index < size) {
                data[index] = data[index + 1];
                index++;
            }
            //Reduces the size, since an index has been removed
            size--;
            //Returns what to remove

        }
        return obj;
    }

    @Override
    //Gets the element at the target index (in this case using "for" loop)
    public Object get(int index){
        //See lin. 28
        if (index < size){
            return data[index];
        }
        else return null;
    }
}
