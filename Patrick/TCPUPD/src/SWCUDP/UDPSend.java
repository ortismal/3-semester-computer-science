package SWCUDP;

import java.io.IOException;
import java.net.*;

public class UDPSend {

    public static void main(String[] args) {

        final int PORT_IN = 5656;
        final int PORT_OUT = 5657;
        final String IP_NAME = "127.0.0.1";

        try {
            DatagramSocket socket = new DatagramSocket(PORT_IN);

            String message = "Hello from UDPSend";

            byte[] data = message.getBytes();
            InetAddress address = InetAddress.getByName(IP_NAME);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT_OUT);

            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
