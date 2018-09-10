public class main {
    public static void main(String[] args) {

        MyArrayList AL = new MyArrayList();

        AL.add(1);
        AL.add(2);
        AL.add(3);
        AL.add(4);
        AL.add(5);
        AL.add(6);
        AL.add(7);
        for (int i = 0; i < AL.size; i++) {
            System.out.println(AL.get(i));

        }
        //Test the size of the ArrayList
        System.out.println("Size of ArrayList: " + AL.size);

        //Test remove,print size go through the ArrayList again
        AL.remove(1);
        for (int i = 0; i < AL.size; i++) {
            System.out.print(AL.get(i) + " ");
        }
        System.out.println("\nNew size of ArrayList : " + AL.size);

    }


}