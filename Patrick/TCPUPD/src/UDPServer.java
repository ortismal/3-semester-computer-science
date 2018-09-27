import java.io.*;
import java.net.*;


public class UDPServer {
    public static void main(String args[]) throws Exception
    {
        String sentence;
        int length;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket receivingSocket = new DatagramSocket(6710);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("10.111.180.4");
        byte[] data = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(data, data.length);
        receivingSocket.receive(receivePacket);
        sentence = new String(receivePacket.getData());
        int size = receivePacket.getLength();
        System.out.println("FROM SERVER size:" + size);
        System.out.println("FROM SERVER:" + sentence);

        System.out.println("Please type you message: ");
        sentence = inFromUser.readLine();
        length = sentence.length();
        data = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, length, IPAddress, 6710);
        sendingSocket.send(sendPacket);
        sentence = " ";
        data = sentence.getBytes();

    }
}
