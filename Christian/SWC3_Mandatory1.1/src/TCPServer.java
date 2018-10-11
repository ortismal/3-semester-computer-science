
import java.io.*;
import java.net.*;

import java.util.ArrayList;

public class TCPServer {


    public static void main(String[] args) {
        System.out.println("=============SERVER==============");

        final int PORT_LISTEN = 5656;
//        Protocol: server -> client:
//        J_OK
//        J_ER <<err_code>>: <<err_msg>>
//        LIST <<name1 name2 name3 …>>

        ArrayList<ClientHandler> clients = new ArrayList<>();


        try {
            ServerSocket server = new ServerSocket(PORT_LISTEN);
            System.out.println("Server starting...\n");

            boolean soc = true;

            while (soc) {
                Socket socket = server.accept();
                System.out.println("Client connected");

                Thread T = new Thread(() -> {
                    try {

                        String msgIn = "";
                        String msgToSend;

                        InputStream input = socket.getInputStream();
                        OutputStream output = socket.getOutputStream();
                        output = socket.getOutputStream();
                        ClientHandler client = new ClientHandler();
                        do {

                            byte[] uName = new byte[1024];
                            input.read(uName);
                            String userNameStr = new String(uName);
                            if (msgIn.contains("JOIN")) {
                                System.out.println(userNameStr);
                            }

                            userNameStr.trim();
                            String user;
                            user = userNameStr.substring(5, userNameStr.lastIndexOf(","));

                            client.setSocket(socket);
                            client.setInput(input);
                            client.setOutput(output);
                            boolean duplicated = false;
                            if (!clients.isEmpty()) {
                                for (ClientHandler c : clients) {
                                    System.out.println("test: " + c.getName());
                                    if (c.getName().equals(user)) {
                                        duplicated = true;
                                    }
                                }
                            }
                            if (!duplicated) {
                                if (checkUser(user)) {
                                    client.setName(user);
                                    clients.add(client);
                                    System.out.println(user);
                                    String clientIp = socket.getInetAddress().getHostAddress();
                                    System.out.println("IP: " + clientIp);
                                    System.out.println("PORT: " + socket.getPort());
                                    sendMessage("\nJ_OK", output);
                                    sendList(clients);
                                    break;
                                }
                                sendMessage("J_ER 0069: Illegal character included, try again!", client.getOutput());
                            } else {
                                sendMessage("J_ER 069: Duplicated username, pick another!", client.getOutput());
                            }

                        } while (true);

                        boolean w = true;

                        while (w) {

                            try {
                                byte[] dataIn = new byte[1024];
                                input.read(dataIn);
                                msgIn = new String(dataIn);
                                msgIn = msgIn.trim();

                            } catch (NullPointerException np) {

                                np.printStackTrace();
                            }

                            System.out.println(msgIn);

                            msgToSend = '\n' + msgIn;

                            if (!msgIn.equals("IMAV")) {

                                for (ClientHandler c : clients) {
                                    sendMessage(msgToSend, c.getOutput());
                                }
                                String[] data = msgIn.split(" ");

                                String testString = data[2];

                                if (testString.length() > 250) {
                                    sendMessage("J_ER 00069: Message too long, try again!", client.getOutput());
                                }
                                if (msgToSend.contains("QUIT")) {
                                    clients.remove(client);
                                    sendList(clients);
                                    socket.close();
                                    w = false;
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                T.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendList(ArrayList<ClientHandler> clients) {
        String clientList = "LIST ";
        for (ClientHandler c : clients) {
            clientList += c.getName() + " ";
        }
        for (ClientHandler c : clients) {
            sendMessage(clientList, c.getOutput());
        }
    }

    public static void sendMessage(String msg, OutputStream output) {
        byte[] sendData = msg.getBytes();

        try {
            output.write(sendData);
            output.flush();
        } catch (IOException ioException) {
//                ioException.printStackTrace();
        }
    }

    public static boolean checkUser(String user) {
        boolean userOkay = false;
        if ((user.matches("^[a-zA-Z\\d-_]{0,12}$"))) {
            userOkay = true;
        }
        return userOkay;
    }
    //To stay:
//    public static boolean checkMessageLength(String msg){
//        boolean msgLengthOkay = true;
//        if ((msg.matches("^(?=.{1,10}$).*"))) {
//            msgLengthOkay = false;
//        }
//        return msgLengthOkay;
//    }
}