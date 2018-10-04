package TECHUDPTCP;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {

        String sentence;
        DatagramSocket receivingSocket = new DatagramSocket(6709);
        DatagramSocket sendingSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

        while (true) {
//        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Scanner inFromKbd = new Scanner(System.in);

            byte[] data = new byte[1024];

            System.out.println("Please type your message");
//            sentence = inFromUser.readLine;
            sentence = inFromKbd.nextLine();
            data = sentence.getBytes();
            if(sentence.equalsIgnoreCase("quit"))
                break;


            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 6710);
            sendingSocket.send(sendPacket);

            sentence = "                                    ";
            data = sentence.getBytes();


            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            receivingSocket.receive(receivePacket);
            sentence = new String(receivePacket.getData());
            if(sentence.equalsIgnoreCase("quit"))
                break;
            System.out.println("FROM SERVER: " + sentence);
        }
    }
}
