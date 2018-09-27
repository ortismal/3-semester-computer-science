import java.io.IOException;
import java.net.*;

public class UPDreceive {

    public static void main(String[] args) {

        final int PORT_IN = 5657;
        final int PORT_OUT = 5656;
        String IP_NAME = "127.0.0.1";


        try {
            DatagramSocket socket = new DatagramSocket(PORT_IN);
            InetAddress adress = InetAddress.getByName(IP_NAME);

            byte[] data;
            String msg;
            do {

                data = new byte[1024];

                DatagramPacket packet = new DatagramPacket(data, data.length, adress, PORT_OUT);
                socket.receive(packet);
                msg = new String(data);
                System.out.println("Received message: " + msg);

            } while (!msg.equalsIgnoreCase("quit"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
