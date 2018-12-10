import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPclient {

    public static void main(String[] args) {
        System.out.println("=============CLIENT==============");

        Scanner sc = new Scanner(System.in);
        System.out.print("What is your username?:");
        String username = args.length >= 1 ? args[0] : sc.nextLine();

        System.out.print("What is the IP for the server (type 0 for localhost): ");
        String ipToConnect = args.length >= 2 ? args[1] : sc.nextLine();

        System.out.print("What is the PORT for the server: ");
        int portToConnect = args.length >= 3 ? Integer.parseInt(args[2]) : sc.nextInt();

        final String USERNAME = username;
        final int PORT_SERVER = portToConnect;
        final String IP_SERVER_STR = ipToConnect.equals("0") ? "127.0.0.1" : ipToConnect;

        try {
            InetAddress ip = InetAddress.getByName(IP_SERVER_STR);

            System.out.println("\nConnecting...");
            System.out.println("USERNAME: " + USERNAME);
            System.out.println("SERVER IP: " + IP_SERVER_STR);
            System.out.println("SERVER PORT: " + PORT_SERVER + "\n");

            Socket socket = new Socket(ip, PORT_SERVER);

            OutputStream outToServer = socket.getOutputStream();
            outToServer.write(("JOIN " + USERNAME + ", " + IP_SERVER_STR + ":" + PORT_SERVER).getBytes());


            while (true) {

                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                sc = new Scanner(System.in);
                System.out.println("Your message: ");
                String msgToSend = sc.nextLine();

                byte[] dataToSend = msgToSend.getBytes();
                output.write(dataToSend);

                byte[] dataIn = new byte[1024];
                input.read(dataIn);
                String msgIn = new String(dataIn);
                msgIn = msgIn.trim();

                System.out.println("IN: " + msgIn);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}