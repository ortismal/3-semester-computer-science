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

        System.out.println(list.toString());

        //Testing size()
        System.out.println("Array size: " + list.size());

        //Testing remove()
        list.remove(6);
        System.out.println(list.toString());
        System.out.println("Array size: " + list.size());

        //Testing remove()
        list.remove(2);
        System.out.println(list.toString());
        System.out.println("Array size: " + list.size());
        list.remove(4);
        System.out.println(list.toString());

    }
}
