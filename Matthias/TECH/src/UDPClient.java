import java.net.*;
import java.util.Scanner;

    /**
     * Take	the	two	exmple	programs	“UDP_11.java” and	“UDP_00.java” and	change	them	so	that
     * they	work,	and	the	two	users	can	send	messages	back	and	forth	for	ever,	until	one	of	them
     * sends	the	message	“quit”,	then	both	sides	shut	down
     */


/**
 * Write a description of class UDP_1 here.
 *
 */

    public class UDPClient {
        public static void main(String args[]) throws Exception {

            Scanner sc = new Scanner(System.in);
            final int PORT_IN = 5656;
            final int PORT_OUT = 5757;
            DatagramSocket receivingSocket = new DatagramSocket(PORT_IN);

            do {
                InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
                byte[] data = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(data, data.length);

                System.out.println("Please type your message: ");
                String msgToSend = sc.nextLine();
                data = msgToSend.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, PORT_OUT);
                receivingSocket.send(sendPacket);
                if(msgToSend.equals("quit")){
                    break;
                }

                receivingSocket.receive(receivePacket);
                String msgIn = new String(receivePacket.getData());

                if(msgIn.equals("quit")) {
                    System.out.println("FROM SERVER: " + msgIn + " - Shutting down!");
                    break;
                }
                System.out.println("FROM SERVER:" + msgIn);

            } while (true);
        }
    }


