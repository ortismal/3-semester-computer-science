import java.io.*;
import java.net.*;

public class TCPServer_test {

    public static void main(String[] args) throws Exception {

        String userTxt;

        System.out.println("Starting TCP Server main program");
        String sentence;

        ServerSocket welcomeSocket = new ServerSocket(5656);
        System.out.println("we have a socket");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("waiting for a connection");
        Socket connectionSocket = welcomeSocket.accept();

        while (true) {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            sentence = inFromClient.readLine();

            if (sentence.equalsIgnoreCase("quit"))
                break;
            System.out.println("FROM CLIENT: " + sentence);
            System.out.print("SERVER: ");

            userTxt = inFromUser.readLine();
            outToClient.writeBytes(userTxt + '\n');
            if (userTxt.equalsIgnoreCase("quit"))
                break;

        }


            connectionSocket.close();
        welcomeSocket.close();
    }
}
