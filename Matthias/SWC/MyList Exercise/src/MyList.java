public interface MyList <T> {

        int size();
        void add(Object x);
        void remove(int index);
        T get(int index);
        String toString();

}