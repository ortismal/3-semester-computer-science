import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by coag on 27-09-2018.
 */
public class TCPAlexClient {

    public static void main(String[] args) {
        System.out.println("=============CLIENT==============");

        Scanner sc = new Scanner(System.in);
        System.out.print("What is the IP for the server (type 0 for localhost): ");
        String ipToConnect = args.length >= 1 ? args[0] : sc.nextLine();

        System.out.print("What is the PORT for the server: ");
        int portToConnect = args.length >= 2 ? Integer.parseInt(args[1]) : sc.nextInt();


        final int PORT_SERVER = portToConnect;
        final String IP_SERVER_STR = ipToConnect.equals("0") ? "127.0.0.1" : ipToConnect;

        try {
            InetAddress ip = InetAddress.getByName(IP_SERVER_STR);

            System.out.println("\nConnecting...");
            System.out.println("SERVER IP: " + IP_SERVER_STR);
            System.out.println("SERVER PORT: " + PORT_SERVER + "\n");

            Socket socket = new Socket(ip, PORT_SERVER);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            sc = new Scanner(System.in);
            System.out.println("What is your username?");
            String msgToSend = "JOIN " + sc.nextLine() + ", " + IP_SERVER_STR + ":" + PORT_SERVER;

            byte[] dataToSend = msgToSend.getBytes();
            output.write(dataToSend);

            byte[] dataIn = new byte[1024];
            input.read(dataIn);
            String msgIn = new String(dataIn);
            msgIn = msgIn.trim();

            System.out.println("IN -->" + msgIn + "<--");

            do {

                System.out.print("Please type your text: ");
                msgToSend = sc.nextLine();
                dataToSend = msgToSend.getBytes();
                output.write(dataToSend);

            } while (!msgToSend.equalsIgnoreCase("quit"));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}