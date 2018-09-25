import java.io.*;
import java.net.*;

public class TCPServer_test {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting TCP Server main program");
        String sentence;
        String userText;
        boolean isQuit = true;

        ServerSocket welcomeSocket = new ServerSocket(5656);
        System.out.println("We have a socket!");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Waiting for a connection..");
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        sentence = inFromClient.readLine();
        while(isQuit) {
            System.out.println("FROM CLIENT: " + sentence);

            System.out.println("Please type your text: ");
            userText = inFromUser.readLine();

            outToClient.writeBytes(userText + '\n');

            sentence = inFromClient.readLine();
            System.out.println("FROM CLIENT: " + sentence);
            if(userText.equalsIgnoreCase("quit") || sentence.equalsIgnoreCase("quit" )){
                isQuit = false;
            }
        }
        connectionSocket.close();
        welcomeSocket.close();

    }

}
