import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        String sentence;
        final int user = 0;
        Client[] clients = new Client[20];

        ServerSocket socket = new ServerSocket(5656);
        System.out.println("Socket created!");
        System.out.println("Waiting for a connection..");

        while(true){

            final Socket s = socket.accept();
            System.out.println("Client request recieved: " + s);
            System.out.println("Creating handler for client.");

            new Thread(()->{
                do {
                    InputStream input = null;
                    OutputStream output = null;
                    try {
                        input = s.getInputStream();
                        output = s.getOutputStream();

                    clients[user] = new Client("Ost", s, input, output);

                    byte[] dataIn = new byte[1024];
                    System.out.println(clients[user].getName() + ": " + input.read(dataIn));

                    /*
                    if (sentence.equalsIgnoreCase("!commands")) {
                        commands();
                    }
                    if (sentence.equalsIgnoreCase("list")) {
                        list(clients);
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                } while (!sentence.equalsIgnoreCase("quit"));
                // outToClient.writeBytes(userName + " has left the chat.");

                System.out.println(userName + " has left the chat!");
                try {
                    s.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println("IP: " + clientIp);
            System.out.println("PORT: " + s.getPort());
            // user++;
        }

        Socket connectionSocket = socket.accept();
        System.out.println("Connection made!");
        String clientIp = socket.getInetAddress().getHostAddress();
        System.out.println("IP: " + clientIp);
        System.out.println("PORT: " + connectionSocket.getPort());

    }
    // Thread opretning af flere brugere på chatten
    /*public static Socket newClient(int user, Thread[] clients, ServerSocket welcomeSocket) {
        clients[clients.length+1] = new Thread(()->{
            try {
                Socket connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }*/

    // Printer en oversigt over forskellige chat-commands.
    public static void commands(){
        System.out.println("List of commands: ");
        System.out.println("1. Quit - leave the server.");;
        System.out.println("2. List - prints a list of active clients");
    }

    // Printer en oversigt over aktive brugere i chatten
    public static void list(Thread[] threads){
        for(int i = 0; i < threads.length; i++){
            System.out.println(threads[i]);
        }
    }

}
