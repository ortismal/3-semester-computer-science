package SWCUDPTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer {

    static Thread t;
    static InputStream input;
    static OutputStream output;

    public static void main(String[] args) {
        System.out.println("=============SERVER==============");

        final int PORT_LISTEN = 5656;

        ArrayList<Client> users = new ArrayList<>();
        Client client = new Client();


        try {

            ServerSocket server = new ServerSocket(PORT_LISTEN);
            System.out.println("Server starting...\n");

            while (true) {


                Socket socket = server.accept();
                System.out.println("Client connected");

                t = new Thread(() -> {

                    InputStream userName = null;
                    try {
                        userName = socket.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] uName = new byte[1024];
                    try {
                        userName.read(uName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String USERNAME = new String(uName);

                    String clientIp = socket.getInetAddress().getHostAddress();
                    System.out.println(USERNAME);
                    System.out.println("IP: " + clientIp);
                    System.out.println("PORT: " + socket.getPort());

                    OutputStream acceptSocket = null;
                    try {
                        acceptSocket = socket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    users.add(new Client(USERNAME, socket, input, output));
                    String clientAccept = "J_OK";

                    byte[] acceptSend = clientAccept.getBytes();
                    try {
                        acceptSocket.write(acceptSend);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(users);

                    while (true) {
                        try {
                            input = socket.getInputStream();
                            output = socket.getOutputStream();

                            byte[] dataIn = new byte[1024];
                            input.read(dataIn);
                            String msgIn = new String(dataIn);
                            msgIn = msgIn.trim();
                            if (!msgIn.equals("IMAV")) {
                                for (Client c : users) {
                                    output = c.getOutput();
                                    output.write(dataIn);
                                }
                            }
                            System.out.println(msgIn);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
