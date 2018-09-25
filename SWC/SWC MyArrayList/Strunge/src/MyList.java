public interface MyList <T> {
    int size();
    void add(Object element);
    T remove(int index);
    T get(int index);
    String toString();
}
