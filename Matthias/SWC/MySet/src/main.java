public class main {

    public static void main (String[] args){

        MySet list = new MySet();

        //Testing add()
        Node node1 = new Node("String1", null);
        Node node2 = new Node("String2", node1);

        list.add(122);
        list.add(122);
        list.add(1);
        list.add(5);
        list.add(7);

        list.add(node2);
        list.add(node1);
        list.add(node1);

        //Testing contains()
        list.contains(122);
        list.contains(node2);
        list.contains(777);

        //Testing size()
        System.out.println("Elements in set: " + list.size());

        //Testing toString()
        System.out.println(list.toString());

        //Testing remove()
        list.remove(node1);
        System.out.println(list.toString());
        list.remove(5);
        System.out.println(list.toString());

        //Testing set dynamics
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        System.out.println(list.toString());
        list.add(17);
        System.out.println("Should add 10 more elements when set is full: ");
        System.out.println(list.toString());

        list.remove(17);
        list.remove(12);
        list.remove(13);
        list.remove(11);
        list.remove(14);

        System.out.println("Should remove 10  elements when set has 10 empty indexes");
        System.out.println(list.toString());

    }

}
