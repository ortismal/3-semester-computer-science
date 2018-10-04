package SWCUDPTCP;

import java.net.*;
import java.util.*;
import java.io.*;

public class TCPClient {

    static Thread IMAV;
    static OutputStream outToServer;
    static InputStream inFromServer;

    public static void main(String[] args) {
        System.out.println("=============CLIENT==============");

        Scanner sc = new Scanner(System.in);

        System.out.println("What is your username: ");
        String userName = args.length >= 1 ? args[0] : sc.nextLine();

        System.out.print("What is the IP for the server (type 0 for localhost): ");
        String ipToConnect = args.length >= 2 ? args[1] : sc.nextLine();

        System.out.print("What is the PORT for the server: ");
        int portToConnect = args.length >= 3 ? Integer.parseInt(args[2]) : sc.nextInt();

        final String USERNAME = userName;
        final int PORT_SERVER = portToConnect;
        final String IP_SERVER_STR = ipToConnect.equals("0") ? "127.0.0.1" : ipToConnect;


        try {
            InetAddress ip = InetAddress.getByName(IP_SERVER_STR);

            System.out.println("\nConnecting...");
            System.out.println("Username: " + USERNAME);
            System.out.println("SERVER IP: " + IP_SERVER_STR);
            System.out.println("SERVER PORT: " + PORT_SERVER + "\n");
            Socket socket = new Socket(ip, PORT_SERVER);
            outToServer = socket.getOutputStream();
            outToServer.write(("JOIN " + USERNAME + ", " + IP_SERVER_STR + ":" + portToConnect).getBytes());

            inFromServer = socket.getInputStream();
            byte[] acceptedClient = new byte[1024];
            inFromServer.read(acceptedClient);
            String msgAccepted = new String(acceptedClient);
            System.out.println(msgAccepted);


            while (true) {

                imavThread();

                inFromServer = socket.getInputStream();
                outToServer = socket.getOutputStream();

                sc = new Scanner(System.in);
                System.out.println("\nWhat do you want to send? ");
                String msgToSend = "DATA " + USERNAME + ": " + sc.nextLine();



                byte[] dataToSend = msgToSend.getBytes();
                outToServer.write(dataToSend);
                if (msgToSend.equalsIgnoreCase("\nDATA " + USERNAME + ": " + "quit")) {
                    System.out.println("Shutting down");
                    break;
                }

                byte[] dataIn = new byte[1024];
                inFromServer.read(dataIn);
                String msgIn = new String(dataIn);
                msgIn = msgIn.trim();
                System.out.println(msgIn);

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void imavThread() {
        IMAV = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(60000);
                    outToServer.write("IMAV".getBytes());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        IMAV.start();
    }


}


