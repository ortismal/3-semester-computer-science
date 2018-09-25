import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {

        String sentence;
        int length;

        Scanner inFromKbd = new Scanner(System.in);
        DatagramSocket receivingSocket = new DatagramSocket(6710);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAdress = InetAddress.getByName("10.111.176.164");
        byte[] data = new byte[1024];

        System.out.println("Please type your message: ");
        sentence = inFromKbd.nextLine();
        length = sentence.length();
        data = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data,  length, IPAdress, 6710);
        sendingSocket.send(sendPacket);
        sentence = "            ";
        data = sentence.getBytes();

        DatagramPacket receivePackage = new DatagramPacket(data, data.length);
        receivingSocket.receive(receivePackage);
        sentence = new String(receivePackage.getData());
        System.out.println("FROM SERVER: " + sentence);
    }
}