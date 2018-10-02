import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        String sentence;
        String userName;
        int user = 0;
        Thread[] clients = new Thread[user];


        ServerSocket welcomeSocket = new ServerSocket(5656);
        System.out.println("Socket created!");
        System.out.println("Waiting for a connection..");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection made!");

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        userName = inFromClient.readLine();

        do{
            sentence = inFromClient.readLine();
            System.out.println(userName + ": " + sentence);
            if(sentence.equalsIgnoreCase("!commands")){
                commands();
            } if(sentence.equalsIgnoreCase("list")){
                list(clients);
            }

        } while (!sentence.equalsIgnoreCase("quit"));
        // outToClient.writeBytes(userName + " has left the chat.");

        System.out.println(userName + " has left the chat!");
        connectionSocket.close();
        welcomeSocket.close();

    }

    /*public static Socket newClient(int user, Thread[] clients, ServerSocket welcomeSocket) {
        clients[clients.length+1] = new Thread(()->{
            try {
                Socket connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }*/

    public static void commands(){
        System.out.println("List of commands: ");
        System.out.println("1. Quit - leave the server.");;
        System.out.println("2. List - prints a list of active clients");
    }

    public static void list(Thread[] threads){
        for(int i = 0; i < threads.length; i++){
            System.out.println(threads[i]);
        }
    }

}
