import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPConnection {

    public static void connect() throws Exception {

        String sentence;
        int length;

        Scanner inFromKbd = new Scanner(System.in);
        DatagramSocket receivingSocket = new DatagramSocket(6701);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAdress = InetAddress.getByName("127.0.0.1");
        byte[] data = new byte[1024];

        System.out.println("Please type your message: ");
        sentence = inFromKbd.nextLine();
        length = sentence.length();
        data = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, 1, IPAdress, 6700);
        sendingSocket.send(sendPacket);
        sentence = "            ";
        data = sentence.getBytes();

        DatagramPacket receivePackage = new DatagramPacket(data, data.length);
        receivingSocket.receive(receivePackage);
        sentence = new String(receivePackage.getData());
        System.out.println("FROM SERVER: " + sentence);
    }
}
