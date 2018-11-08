import javax.imageio.IIOException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPSend {

    //private UDPSend(){}

    public static void main(String[] args){

        final int PORT_IN = 5656;
        final int PORT_OUT = 5657;
        final String IP_NAME = "127.0.0.1";

        try {
            System.out.println();
            DatagramSocket socket = new DatagramSocket(PORT_IN);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("What do you want to send? ");
                String msg = sc.nextLine();

                byte[] data = msg.getBytes();
                //localhost
                //127.0.0.1
                //0.0.0.0
                //4a:3f:56:.. (IP ver. 4)
                InetAddress address = InetAddress.getByName(IP_NAME);


                DatagramPacket packet = new DatagramPacket(data, data.length, address, PORT_OUT);

                socket.send(packet);
                System.out.println("Done!");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
