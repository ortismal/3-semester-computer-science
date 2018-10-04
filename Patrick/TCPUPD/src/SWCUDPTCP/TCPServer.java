package SWCUDPTCP;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        System.out.println("=============SERVER==============");

        final int PORT_LISTEN = 5656;
//        Threads
//        JOIN <<username>>, <<server ip>> <<server port>>

        try {
            ServerSocket server = new ServerSocket(PORT_LISTEN);

            System.out.println("Server starting...\n");

            Socket socket = server.accept();
            System.out.println("Client connected");

            InputStream userName = socket.getInputStream();
            byte[] uName = new byte[1024];
            userName.read(uName);
            String USERNAME = new String(uName);

            String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println(USERNAME);
            System.out.println("IP: " + clientIp);
            System.out.println("PORT: " + socket.getPort());

            OutputStream acceptSocket = socket.getOutputStream();
            String clientAccept = "YOU ARE CONNECTED\n";
            byte[] acceptSend = clientAccept.getBytes();
            acceptSocket.write(acceptSend);

//            if (join){
//                new Thread(user).start();
//            }

            while (true) {
//                System.out.println("USER: " + clientIp);
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                byte[] dataIn = new byte[1024];
                input.read(dataIn);
                String msgIn = new String(dataIn);
                msgIn = msgIn.trim();

                System.out.println(msgIn);

                String msgToSend = msgIn;
                byte[] dataToSend = msgToSend.getBytes();
                output.write(dataToSend);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
