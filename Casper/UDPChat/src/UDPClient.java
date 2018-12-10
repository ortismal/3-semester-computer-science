import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {

        String sentence;
        int length;

        DatagramSocket receivingSocket = new DatagramSocket(6710);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAdress = InetAddress.getByName("127.0.0.1");

        while (true) {
            Scanner inFromKbd = new Scanner(System.in);
            byte[] data;


            System.out.println("Please type your message: ");
            sentence = inFromKbd.nextLine();
            length = sentence.length();
            data = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, length, IPAdress, 6701);


            sendingSocket.send(sendPacket);
            data = sentence.getBytes();

            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }


            DatagramPacket receivePackage = new DatagramPacket(data, data.length);
            receivingSocket.receive(receivePackage);
            sentence = new String(receivePackage.getData());
            System.out.println("FROM SERVER: " + sentence);

            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }
}
