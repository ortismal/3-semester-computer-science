
import java.util.*;
import java.net.*;
import java.io.*;

public class TCPClient extends Thread {
    static InputStream input;
    static OutputStream outputToServer;
    static Thread T;
    static Thread T2;
    static String msgToSend;
    static boolean stop = true;



    public static void main(String[] args) {
        System.out.println("=============CLIENT==============");

        Scanner sc = new Scanner(System.in);

        System.out.print("What is the IP for the server (type 0 for localhost): ");
        String ipToConnect = args.length >= 1 ? args[0] : sc.nextLine();

        System.out.print("What is the PORT for the server: ");
        int portToConnect = args.length >= 2 ? Integer.parseInt(args[1]) : sc.nextInt();

        final int PORT_SERVER = portToConnect;
        final String IP_SERVER_STR = ipToConnect.equals("0") ? "127.0.0.1" : ipToConnect;

//        Protocol client -> server:
//        JOIN <<user_name>>, <<server_ip>>:<<server_port>>
//        DATA <<user_name>>: <<free text…>>
//        IMAV
//        QUIT

        try {
            InetAddress ip = InetAddress.getByName(IP_SERVER_STR);

            System.out.println("\nConnecting...");
            System.out.println("SERVER IP: " + IP_SERVER_STR);
            System.out.println("SERVER PORT: " + PORT_SERVER + "\n");
            Socket socket = new Socket(ip, PORT_SERVER);
            outputToServer = socket.getOutputStream();
            input = socket.getInputStream();
            String userName;
            System.out.println(sc.nextLine());

            do {
                System.out.print("What is your username: ");
                userName = sc.nextLine();
                String msgOut = "JOIN " + userName + ", " + IP_SERVER_STR + ":" + portToConnect;
                byte[] dataOut = msgOut.getBytes();
                outputToServer.write(dataOut);

                byte[] acceptedClient = new byte[1024];
                input.read(acceptedClient);
                String msgFromServer = new String(acceptedClient);
                msgFromServer.trim();
                if (msgFromServer.contains("J_OK")){
                    System.out.println(msgFromServer);
                    break;
                }
                System.out.println(msgFromServer);

            } while (true);

            msgThread();
            IMAVthread();

            while (true) {

                outputToServer = socket.getOutputStream();

                sc = new Scanner(System.in);
                System.out.println("What do you want to send? ");

                msgToSend = "DATA " + userName + ": " + sc.nextLine();

                byte[] dataToSend = msgToSend.getBytes();
                outputToServer.write(dataToSend);

                if (msgToSend.equals("DATA " + userName + ": " + "QUIT")) {
                    System.out.println("Shutting down");
                    outputToServer.close();
                    input.close();
                    socket.close();
                    T.stop();
                    T2.stop();
                    break;
                }
            }
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void IMAVthread() {
        T = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(40000);
                    outputToServer.write("IMAV".getBytes());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        T.start();
    }

    public static void msgThread() {
        T2 = new Thread(() -> {
            while (true) {
                try {
                    byte[] dataIn = new byte[1024];
                    input.read(dataIn);
                    String msgIn = new String(dataIn);
                    msgIn = msgIn.trim();
                    System.out.println(msgIn);

                } catch (NullPointerException ne) {
                    ne.printStackTrace();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        T2.start();
    }
}