public class MyArrayListMain {
    public static void main(String[] args){

        MyArrayList test = new MyArrayList();
        test.add(10);
        test.add(20);
        test.add(30);
        test.add(40);
        test.add(50);
        test.add(60);
        test.add(70);
        for (int i = 0; i < test.size(); i++){
            System.out.print(test.get(i)+ " ");
        }
        System.out.println("Length of current ArrayList : " + test.size());
        System.out.println("Check index 3 : " + test.get(3) + " Is at index 3.");
        System.out.println("Removing the element at index 2, which is : " + test.remove(2) + "\nAnd hereafter, check the arraylist again.");
        for (int i = 0; i < test.size(); i++){
            System.out.print(test.get(i) + " ");
        }
        System.out.println("\nAmount of indexes : " + test.size());
    }
}
