package SWCUDPTCP;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        System.out.println("=============SERVER==============");

        final int PORT_LISTEN = 5656;
        int user = 0;
        Thread[] clients = new Thread[user];

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
            String clientAccept = "J_OK";
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
                if (!msgIn.equals("IMAV")){
                    output.write(dataIn);
                }
                System.out.println(msgIn);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
