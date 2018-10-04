import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPSend {

    public static void main(String[] args) {

        final int PORT_IN = 5656;
        final int PORT_OUT = 5657;
        final String IP_NAME = "127.0.0.1";

        try {

            DatagramSocket socket = new DatagramSocket(PORT_IN);
            Scanner sc = new Scanner(System.in);

            while(true) {
                System.out.println("What do you want to send?");

                String msg = sc.nextLine();
                byte[] data = msg.getBytes();

                InetAddress address = InetAddress.getByName(IP_NAME);
                DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT_OUT);

                socket.send(packet);
                System.out.println("Message sent!");
            }

        } catch(SocketException e) {
            e.printStackTrace();
        } catch(UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

}
