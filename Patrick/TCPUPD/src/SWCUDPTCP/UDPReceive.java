package SWCUDPTCP;

import java.io.IOException;
import java.net.*;

public class UDPReceive {

    public static void main(String[] args) {

        final int PORT_IN = 5657;
        final int PORT_OUT = 5656;
        final String IP_NAME = "127.0.0.1";

        try {
            DatagramSocket socket = new DatagramSocket(PORT_IN);
            InetAddress address = InetAddress.getByName(IP_NAME);

            String msg = "";
            do {
                byte[] data = new byte[1024];

                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                msg = new String(data).trim();
                System.out.println(msg);

            } while (!msg.equalsIgnoreCase("#die"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
