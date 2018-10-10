package SWCUDPTCP;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer {

    static Thread t;
    static InputStream input;
    static OutputStream output;
    static Socket socket;

    public static void main(String[] args) {
        System.out.println("=============SERVER==============");

        final int PORT_LISTEN = 5656;

        ArrayList<Client> users = new ArrayList<>();


        try {

            ServerSocket server = new ServerSocket(PORT_LISTEN);
            System.out.println("Server starting...\n");



            while (true) {
                Client client = new Client();
                socket = server.accept();
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
                    USERNAME = USERNAME.substring(5, USERNAME.lastIndexOf(","));


                    String clientIp = socket.getInetAddress().getHostAddress();
                    System.out.println(USERNAME);
                    System.out.println("IP: " + clientIp);
                    System.out.println("PORT: " + socket.getPort());

                    OutputStream acceptSocket = null;
                    try {
                        acceptSocket = socket.getOutputStream();
                        input = socket.getInputStream();
                        output = socket.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    client.setName(USERNAME);
                    client.setSocket(socket);
                    client.setOutput(output);
                    client.setInput(input);

                    users.add(client);
                    String clientAccept = "J_OK";
                    System.out.println(users);
                    System.out.println("JOIN " + USERNAME + ", " + clientIp + ":" + PORT_LISTEN);
                    byte[] acceptSend = clientAccept.getBytes();
                    try {
                        acceptSocket.write(acceptSend);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        ListOfClients(users);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


//                    String ClientList = "";
//                    for (Client c : users) {
//                        ClientList += c.getName() + ", ";
//                    }
//                    for (Client c : users) {
//                        output = c.getOutput();
//                        String cList = "List of all clients: " + ClientList;
//                        byte[] listofclients;
//                        listofclients = cList.getBytes();
//                        try {
//                            output.write(listofclients);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }


                    do {
                        try {
                            input = socket.getInputStream();
                            output = socket.getOutputStream();

                            byte[] dataIn = new byte[1024];
                            input.read(dataIn);
                            String msgIn = new String(dataIn);
                            msgIn = msgIn.trim();



                            if (msgIn.equalsIgnoreCase("!quit")) {
                                users.remove(client);
                                socket.close();
                                ListOfClients(users);
                                break;
                            }
                            for (Client c : users) {

                                if (msgIn.trim().length() > 250) {
                                    byte[] J_ER_TooLong;
                                    String J_ER_long = "J_ER MESSAGE TOO LONG: Message contains: " + msgIn.length() + " characters, max length is 250";
                                    J_ER_TooLong = J_ER_long.getBytes();
                                    output.write(J_ER_TooLong);
                                } else if (!msgIn.equals("IMAV") && !msgIn.contains("!Quit")) {
                                    output = c.getOutput();
                                    output.write(dataIn);
                                } else {
                                    System.out.println(msgIn);
                                }
                                System.out.println(msgIn);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } while (!socket.isClosed());
                });
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void ListOfClients(ArrayList<Client> users) throws IOException {

        String clientList = "";
        for (Client c : users) {
            clientList += c.getName() + ", ";
            output = c.getOutput();
            String cList = "List of all clients: " + clientList.substring(0, clientList.lastIndexOf(","));
            byte[] listofclients;
            listofclients = cList.getBytes();
            output.write(listofclients);
        }
    }

}
