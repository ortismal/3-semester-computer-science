import java.util.Arrays;

public class MyArrayList<T> implements MyList {

    private T[] data = (T[]) new Object[5];
    private int size = 0;

    @Override
    //Check for current length
    public int size() {
        return data.length;
    }

    @Override
    //Add to current list
    public void add(Object element) {
        //Check if there is anymore room in the list
        if (data.length - size < 1){
            //Copies old array, and adds its indexes to a new array, which is longer
            data = Arrays.copyOf(data, data.length*2);
        }
        //Increments size, and sets the element as the same as size, at size 5 it does (5*2) so that Object[5] becomes Object[10]
        data[size++] = (T) element;
    }

    @Override
    public Object remove(int index) {
        //Checks if the index is within the array
        if (index < size){
            Object obj = data[index];
            int temp = index;
            while(temp < size){
                data[temp] = data[temp+1];
                temp++;
            }
            //Reduces the size, since an index has been removed
            size--;
            //Returns what to remove
            return obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    //Gets the element at the target index (in this case using "for" loop)
    public Object get(int index) {
        //See lin. 28
        if (index < size){
            return data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void main(String[] args){

        MyArrayList test = new MyArrayList();
        test.add(10);
        test.add(20);
        test.add(30);
        test.add(40);
        test.add(50);
        test.add(60);
        test.add(70);
        for (int i = 0; i < test.size; i++){
            System.out.print(test.get(i)+ " ");
        }
        System.out.println("Length of current ArrayList : " + test.size);
        System.out.println("Check index 3 : " + test.get(3) + " Is at index 3.");
        System.out.println("Removing the element at index 4, which is : " + test.remove(4) + "\nAnd hereafter, check the arraylist again.");
        for (int i = 0; i < test.size; i++){
            System.out.print(test.get(i) + " ");
        }
        System.out.println("\nAmount of indexes : " + test.size);
    }

}
