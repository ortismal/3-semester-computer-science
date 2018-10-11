import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by Matthias Skou on 02-10-2018.
 */

public class TCPServer {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        ArrayList<Client> clients = new ArrayList<>();
        ServerSocket socket = new ServerSocket(5656);

        System.out.println("Socket created!");
        System.out.println("Server listening");

        while (true) {
            final Socket s = socket.accept();
            System.out.println("\nClient request received: " + s);

            // Client thread
            Thread threads = new Thread(() -> {
                try {
                    Client client = new Client();
                    InputStream input = s.getInputStream();
                    OutputStream output = s.getOutputStream();

                    String msgIn;
                    String userName;

                    do {
                        msgIn = receiveMsg(input);
                        userName = msgIn.substring(5, msgIn.lastIndexOf(","));

                        client.setS(s);
                        client.setInput(input);
                        client.setOutput(output);
                        // If username doesn't match regex
                        if (!userName.matches("^[a-zA-Z\\d-_]{0,12}$")) {
                            sendMsg("J_ER 01: Username is max 12 chars long, only letters, digits, ‘-‘ and ‘_’ allowed.", output);
                            // If username is duplicate
                        }else if (userName.equalsIgnoreCase("QUIT") || userName.equalsIgnoreCase("LIST") ) {
                            sendMsg("J_ER 02: QUIT and LIST are reserved keywords - try again", output);
                        } else if (isDuplicate(userName, clients)) {
                            sendMsg("J_ER 03: Name already exists", output);
                        // If no current users matches given username(NOTE: Not case sensitive)
                        } else if (!isDuplicate(userName, clients)) {
                            client.setName(userName);
                            clients.add(client);
                            System.out.println(msgIn);
                            sendMsg("J_OK\nWelcome to the chat " + userName + "!\nFor a list of available commands, type !commands or !help", output);
                            list(clients, output, true);
                            break;
                        }
                    } while (true);

                    do {
                        msgIn = receiveMsg(input);
                        // If received msg doesn't follow DATA <<user_name>>: <<free text…>> protocol - return J_ER  or print IMAV
                        if (msgIn.contains("DATA " + userName + ": ")) {
                            // If received msg is !commands or !help - return list of commands
                            if (msgIn.equalsIgnoreCase("DATA " + userName + ": " + "!commands") || msgIn.equalsIgnoreCase("DATA " + userName + ": " + "!help")) {
                                sendMsg("List of available commands:\n1. QUIT - leave the server. \n2. LIST - prints a list of active clients", output);
                            }
                            // (Extra functionality)If received msg is LIST - return list of active users to specific client
                            if (msgIn.equalsIgnoreCase("DATA " + userName + ": " + "LIST")) {
                                list(clients, output, false);
                            }
                            // Maximum message length is 250 characters
                            if (msgIn.length() > 250) {
                                sendMsg("\nMaximum length of message is 250 characters, try again.", output);
                            } else {
                                System.out.print("\n" + msgIn);
                                for (Client c : clients) {
                                    sendMsg("\n" + msgIn, c.getOutput());
                                }
                            }
                            // If received msg is QUIT - Remove client, return updated list to all clients, close socket and break loop
                        } else if (msgIn.equals("QUIT")) {
                            // sendMsg("You have quit the chat!", output);
                            clients.remove(client);
                            list(clients, output, true);
                            s.close();
                            break;
                            // If msg received is IMAV - print username + IMAV
                        } else if (msgIn.equalsIgnoreCase("IMAV")) {
                            System.out.println("\n" + userName + " IMAV");
                            // Msg received doesn't follow DATA Protocol - return J_ER
                        } else {
                            sendMsg("J_ER 04 : Unknown command - Syntax needed: \"DATA <<user_name>>: <<free text…>>\"", output);
                        }
                    } while (true);
                    System.out.println("\n" + client.getName() + " has left the chat!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threads.start();
        }

    }

    // Sends list of active users to all clients
    public static void list(ArrayList<Client> clients, OutputStream output, boolean isAll) {
        // (Extra functionality) Updated list format to [name, name] but doesn't follow LIST <<name1 name2 name3 …>> protocol
        String list = "Active clients: [";
        if (!clients.isEmpty()) {
            for (Client c : clients) {
                list = list + c.getName() + ", ";
            }
            list = list.substring(0, list.lastIndexOf(",")) + "]";
            // If list needs to be sent to all active clients
            if (isAll) {
                for (Client c : clients) {
                    sendMsg(list, c.getOutput());
                }
            } else {
                sendMsg(list, output);
            }
        }
    }

    // Check if name already exists(NOTE: Not case sensitive)
    public static boolean isDuplicate(String userName, ArrayList<Client> clients) {
        for (Client c : clients) {
            if (c.getName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    // Writes data to client
    public static void sendMsg(String msg, OutputStream output) {
        byte[] dataOut = msg.getBytes();
        try {
            output.write(dataOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Receives messages from client
    public static String receiveMsg(InputStream input) {
        String msgIn;
        byte[] dataIn = new byte[1024];
        try {
            input.read(dataIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        msgIn = new String(dataIn);
        msgIn = msgIn.trim();
        return msgIn;
    }
}
