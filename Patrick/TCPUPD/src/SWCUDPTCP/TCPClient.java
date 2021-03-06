package SWCUDPTCP;

import java.net.*;
import java.util.*;
import java.io.*;

public class TCPClient {

    static Thread msgFromServer;
    static Thread IMAV;
    static OutputStream outToServer;
    static InputStream inFromServer;
    static String USERNAME;

    public static void main(String[] args) {
        System.out.println("=============CLIENT==============");

        Scanner sc = new Scanner(System.in);

        System.out.println("What is your username: ");


        do {
            // tjek om username overholder protokollen. Loop hvis ikke.
            String userName = args.length >= 1 ? args[0] : sc.nextLine();
            if ((userName.matches("^[a-zA-Z\\d-_]{0,12}$"))) {
                USERNAME = userName;
                break;
            }
            System.out.println("Username is too long. Max 12 digits or letters and only '_', '-' is allowed. \n Please pick a new username");
        } while (true);

        System.out.print("What is the IP for the server (type 0 for localhost): ");
        String ipToConnect = args.length >= 2 ? args[1] : sc.nextLine();

        System.out.print("What is the PORT for the server: ");
        int portToConnect = args.length >= 3 ? Integer.parseInt(args[2]) : sc.nextInt();


        final int PORT_SERVER = portToConnect;
        final String IP_SERVER_STR = ipToConnect.equals("0") ? "127.0.0.1" : ipToConnect;


        try {


            InetAddress ip = InetAddress.getByName(IP_SERVER_STR);
            Socket socket = new Socket(ip, PORT_SERVER);


            System.out.println("\nConnecting...");
            System.out.println("Username: " + USERNAME);
            System.out.println("SERVER IP: " + IP_SERVER_STR);
            System.out.println("SERVER PORT: " + PORT_SERVER + "\n");

            outToServer = socket.getOutputStream();
            outToServer.write(("JOIN " + USERNAME + ", " + IP_SERVER_STR + ":" + portToConnect).getBytes());

            inFromServer = socket.getInputStream();
            byte[] acceptedClient = new byte[1024];
            inFromServer.read(acceptedClient);
            String msgAccepted = new String(acceptedClient);

            //Tjek for duplicate username (ikke testet)
            if (msgAccepted.equalsIgnoreCase("J_ER")) {
                sc = new Scanner(System.in);
                System.out.println("What is your new username: ");
                USERNAME = sc.nextLine();
                System.out.println("new username = " + USERNAME);
                byte[] newUserName = USERNAME.getBytes();
                outToServer.write(newUserName);
            }

            //start IMAV og receieMsg.
            imavThread();
            receiveMsg();


            //tillad at man kan sende beskeder uafhængigt af at modtage beskeder.
            while (true) {


                outToServer = socket.getOutputStream();

                sc = new Scanner(System.in);

                do {
                    System.out.println("\nWhat do you want to send? ");
                    String msgToSend = "DATA " + USERNAME + ": " + sc.nextLine();
                    byte[] dataToSend = msgToSend.getBytes();

                    if (msgToSend.equalsIgnoreCase("DATA " + USERNAME + ": " + "!quit")) {
                        msgFromServer.stop();
                        IMAV.stop();
                        System.out.println("Shutting down");
                    }

                    if (msgToSend.trim().length() < 250) {
                        outToServer.write(dataToSend);
                        break;
                    }
                    System.out.println("Your message length is: " + msgToSend.trim().length() + " which is " + (msgToSend.trim().length() - 250) + " too long, your message can be no longer than 250 characters long.");
                } while (true);


            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // modtag beskeder fra serveren uafhængig af at sende beskeder.
    static void receiveMsg() {
        msgFromServer = new Thread(() -> {
            try {
                while (true) {

                    byte[] dataIn = new byte[1024];
                    inFromServer.read(dataIn);
                    String msgIn = new String(dataIn);
                    msgIn = msgIn.trim();
                    System.out.println(msgIn);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        msgFromServer.start();
    }

    //start IMAV
    static void imavThread() {
        IMAV = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(40000);
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