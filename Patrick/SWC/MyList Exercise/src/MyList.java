public interface MyList <T> {
    int size();
    void add(Object element);
    void remove(int index);
    T get(int index);
    String toString();
}
