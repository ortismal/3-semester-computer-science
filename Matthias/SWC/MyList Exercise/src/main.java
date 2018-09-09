public class main {

    public static void main(String [] args){
        MyArrayList list = new MyArrayList();

        //Testing add()
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        print(list);

        //Testing size()
        System.out.println("Array size: " + list.size());

        //Testing remove()
        list.remove(7);
        print(list);
        System.out.println("Array size: " + list.size());

    }

        //Testing get()
    public static void print(MyArrayList list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
