public interface MyList <T> {

        int size();
        void add(Object x);
        T remove(int index);
        T get(int index);
        String toString();

        }