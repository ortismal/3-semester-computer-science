import java.io.*;
import java.net.*;
public class UDP_Server {
    public static void main(String args[]) throws Exception
    {
        String sentence;
        int length;

        DatagramSocket receivingSocket = new DatagramSocket(6701);
        DatagramSocket sendingSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            byte[] data = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(data, 33);
            receivingSocket.receive(receivePacket);
            sentence = new String(receivePacket.getData());
            int size = receivePacket.getLength();
            System.out.println("FROM SERVER size:" + size);
            System.out.println("FROM SERVER:" + sentence);
            sentence = sentence.trim();
            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Please type you message: ");
            sentence = inFromUser.readLine();
            length = sentence.length();
            data = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(data, length, IPAddress, 6710);
            sendingSocket.send(sendPacket);
            //sentence = " ";
            sentence.getBytes();
            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }
}
