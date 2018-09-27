import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[]args)throws Exception{

        String sentence;

//        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Scanner inFromKbd = new Scanner(System.in);
        DatagramSocket receivingSocket = new DatagramSocket(6710);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("10.111.180.4");
        byte[] data = new byte[1024];

            System.out.println("Please type your message");
//            sentence = inFromUser.readLine;
            sentence = inFromKbd.nextLine();
            data = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 6710);
            sendingSocket.send(sendPacket);
            sentence = "                                    ";
            data = sentence.getBytes();

            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            receivingSocket.receive(receivePacket);
            sentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER: " + sentence);
    }
}
