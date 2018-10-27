/**
 * Write a description of class UDP_0 here.
 *
 */

import java.net.*;
import java.util.Scanner;
    /**
     * Take	the	two	exmple	programs	“UDP_11.java” and	“UDP_00.java” and	change	them	so	that
     * they	work,	and	the	two	users	can	send	messages	back	and	forth	for	ever,	until	one	of	them
     * sends	the	message	“quit”,	then	both	sides	shut	down
     */

    public class UDPServer {
        public static void main(String args[]) throws Exception {

            Scanner sc = new Scanner(System.in);
            final int PORT_IN = 5757;
            final int PORT_OUT = 5656;
            DatagramSocket receivingSocket = new DatagramSocket(PORT_IN);

            DatagramSocket sendingSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("127.0.0.1");

            do {

                byte[] data = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(data, data.length);
                receivingSocket.receive(receivePacket);
                String msgIn = new String(receivePacket.getData());
                if(msgIn.equals("quit")){
                    System.out.println("FROM CLIENT: " + msgIn + " - Shutting down!");
                    break;
                }

                int size = receivePacket.getLength();
                System.out.println("FROM CLIENT size:" + size);
                System.out.println("FROM CLIENT:" + msgIn);

                System.out.println("Please type your message: ");
                String msgToSend = sc.nextLine();
                data = msgToSend.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, PORT_OUT);
                sendingSocket.send(sendPacket);
            } while(true);
        }
    }



