import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        ArrayList<Client> clients = new ArrayList<>();
        ServerSocket socket = new ServerSocket(5656);
        Client client = new Client();

        System.out.println("Socket created!");
        System.out.println("Waiting for a connection..");

        while (true) {

            final Socket s = socket.accept();
            System.out.println("Client request recieved: " + s);

            Thread threads = new Thread(() -> {
                final int ID = client.getID();
                byte[] dataIn = new byte[1024];
                byte[] dataOut = new byte[1024];
                InputStream input = null;
                OutputStream output = null;
                String msgIn = null;
                String msgOut = null;

                try {
                    output = s.getOutputStream();
                    input = s.getInputStream();
                    input.read(dataIn);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                msgIn = new String(dataIn);
                msgIn = msgIn.trim();
                clients.add(new Client(ID, msgIn.substring(5, msgIn.indexOf(",")), s, input, output));
                client.increment();
                System.out.println(msgIn);

                msgOut = "J_OK";

                try {
                    dataOut = msgOut.getBytes();
                    output.write(dataOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                do {
                    dataIn = new byte[1024];
                    try {
                        clients.get(ID).getInput().read(dataIn);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    msgIn = new String(dataIn);
                    msgIn = msgIn.trim();
                    if (msgIn.equalsIgnoreCase("!commands")) {
                        commands();
                    }
                    if (msgIn.equalsIgnoreCase("!list")) {
                        list(clients);
                    } else {
                        System.out.println("DATA " + clients.get(ID).getName() + ": " + msgIn);
                    }

                } while (!msgIn.equalsIgnoreCase("quit"));

                // outToClient.writeBytes(userName + " has left the chat.");

                System.out.println(clients.get(ID).getName() + " has left the chat!");
                try {
                    s.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            /*String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println("IP: " + clientIp);
            System.out.println("PORT: " + s.getPort());*/
            threads.start();
        }

    }

    // Printer en oversigt over forskellige chat-commands.
    public static void commands() {
        System.out.println("List of commands: ");
        System.out.println("1. Quit - leave the server.");
        ;
        System.out.println("2. List - prints a list of active clients");
    }

    // Printer en oversigt over aktive brugere i chatten
    public static void list(ArrayList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(clients.get(i).toString());
        }
    }

    public static boolean isDuplicate(ArrayList<Client> clients) {
        return true;
    }

}
