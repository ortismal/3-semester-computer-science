package SWCUDP;

import java.io.IOException;
import java.net.*;

public class UDPReceive {

    public static void main(String[] args) {

        final int PORT_IN = 5657;
        final int PORT_OUT = 5656;
        final String IP_NAME = "127.0.0.1";

        try {
            DatagramSocket socket = new DatagramSocket(PORT_IN);
            byte[] data = new byte[1024];

            InetAddress address = InetAddress.getByName(IP_NAME);
            DatagramPacket packet = new DatagramPacket(data, data.length);

            socket.receive(packet);
            String msg = new String(data);
            System.out.println(msg);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
