import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Matthias Skou on 02-10-2018.
 */

public class TCPClient {

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
            String userName;
            String msgToSend;
            String msgIn;

            // Do-while until J_OK msg received
            do {
                // Do-while until username matches regex
                do {
                    System.out.println("What is your username?");
                    userName = sc.nextLine();
                    // Username is max 12 chars long, only letters, digits, ‘-‘ and ‘_’ allowed.
                    if ((userName.matches("^[a-zA-Z\\d-_]{0,12}$"))) {
                            break;
                    }
                    System.out.println("Username is max 12 chars long, only letters, digits, ‘-‘ and ‘_’ allowed.");
                } while (true);

                // JOIN <<user_name>>, <<server_ip>>:<<server_port>> Protocol
                sendMsg("JOIN " + userName + ", " + IP_SERVER_STR + ":" + PORT_SERVER, output);
                msgIn = receiveMsg(input);

                if (msgIn.contains("J_OK")) {
                    System.out.println(msgIn);
                    break;
                }
                // Print error message from server
                System.out.println(msgIn);
            } while (true);

            // Thread ready to receive messages from server
            Thread receive = new Thread(() -> {
                while (true) {
                    System.out.println("\n" + receiveMsg(input));
                }
            });

            // Client sends this heartbeat alive every 1 minute(60.000 milliseconds).
            Thread heartBeat = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(60000);
                        sendMsg("\nIMAV", output);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            // Starting threads
            heartBeat.start();
            receive.start();

            // Do-while until user types 'QUIT'
            do {
                //Do-while until user message is smaller than 250 characters.
                do {
                    System.out.print("Please type your text: ");
                    msgToSend = sc.nextLine();
                    if (msgToSend.length() < 249) {
                        break;
                    }
                    System.out.println("Maximum length of message is 250 characters, try again.");
                } while (true);
                // If client msg is 'QUIT' - Client is closing down and leaving the group.
                if (msgToSend.equals("QUIT")) {
                    System.out.println("You have left the chat!");
                    sendMsg("QUIT", output);
                    socket.close();
                    System.exit(0);
                    break;
                }
                // Send client msg to server - DATA <<user_name>>: <<free text…>>
                sendMsg("DATA " + userName + ": " + msgToSend, output);
            } while (true);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that sends message to server
    public static void sendMsg(String msg, OutputStream output) {
        byte[] dataOut = msg.getBytes();
        try {
            output.write(dataOut);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that receives message from server
    public static String receiveMsg(InputStream input) {
        byte[] dataIn = new byte[1024];
        try {
            input.read(dataIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msgIn = new String(dataIn);
        msgIn = msgIn.trim();
        return msgIn;
    }
}