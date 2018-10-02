import java.io.*;
import java.net.*;

class TCPClient  {
    public static void main(String argv[]) throws Exception {
        System.out.println("Starting TCPClient");
        String sentence;
        final int PORT = 5656;
        final String IP = "127.0.0.1";

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Trying to connect");
        Socket clientSocket = new Socket(IP, PORT);
        System.out.println("Connected");
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(userName(inFromUser));

        do {

            System.out.print("Please type your text: ");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');


        } while (!sentence.equalsIgnoreCase("quit"));

        System.out.println("You have left the chat.");

        clientSocket.close();

    }

    public static String userName(BufferedReader inFromUser) throws Exception{
        String userName;
        do {
            System.out.println("Please enter your username: ");
            userName = inFromUser.readLine() + '\n';
        } while (userName.length() > 12);

        return userName;
    }

}