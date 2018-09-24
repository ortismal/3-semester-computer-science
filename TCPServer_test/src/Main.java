import java.net.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        String sentence;
        String userText;

        ServerSocket welcomeSocket = new ServerSocket(5656);
        System.out.println("we have a socket");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("waiting for a connection");
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream((connectionSocket.getOutputStream()));
        sentence = inFromClient.readLine();
        System.out.println("FROM CLIENT" + sentence);

        System.out.println("Please type in your text: ");
        userText = inFromUser.readLine();
        System.out.println("FROM CLIENT: " + sentence);

        connectionSocket.close();
        welcomeSocket.close();

    }
}
