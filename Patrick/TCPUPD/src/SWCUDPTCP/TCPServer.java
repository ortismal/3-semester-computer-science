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
// Client thread
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

                    //Tilføjer client til users ArrayList
                    users.add(client);
                    System.out.println(client);
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


                    //modtag beskeder
                    do {
                        try {
                            byte[] dataIn = new byte[1024];
                            input.read(dataIn);
                            String msgIn = new String(dataIn);
                            msgIn = msgIn.trim();
                            System.out.println(msgIn);
                            if (msgIn.equalsIgnoreCase("DATA " + USERNAME + ": !quit")) {
                                client.getSocket().close();
                                users.remove(client);
                                ListOfClients(users);
                                System.out.println(users);
                            }
                            //Tjek om beskeden er over 250 karaktere, send error tilbage hvis sandt.
                            if (msgIn.trim().length() > 250) {
                                byte[] J_ER_TooLong;
                                String J_ER_long = "J_ER MESSAGE TOO LONG: Message contains: " + msgIn.length() + " characters, max length is 250";
                                J_ER_TooLong = J_ER_long.getBytes();
                                output.write(J_ER_TooLong);
                            } else {
                                //send beskeden til alle brugere.
                                for (Client c : users) {
                                    if (!msgIn.equals("IMAV") && !msgIn.equalsIgnoreCase("DATA " + USERNAME + ": !quit")) {
                                        output = c.getOutput();
                                        output.write(dataIn);
                                    }
                                }
                            }
//                                }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } while (!socket.isClosed());

//                    }
                });
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metode som sender en liste af alle aktive klienter ud til alle aktive brugere. Bliver kaldt ved ændring.
    static void ListOfClients(ArrayList<Client> users) throws IOException {

        String clientList = "";
        for (Client c : users) {
            clientList += c.getName() + ", ";
        }
        for (Client c : users) {
            output = c.getOutput();
            String cList = "List of all clients: " + clientList.substring(0, clientList.lastIndexOf(","));
            byte[] listofclients;
            listofclients = cList.getBytes();
            output.write(listofclients);
        }
    }

}
