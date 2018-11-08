import java.io.*;
import java.net.*;
public class TCP_Server {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting server main program");
        String sentence;
        String userTxt;

        ServerSocket initSocket = new ServerSocket(5656);
        System.out.println("Socket aquired");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Waiting for a connection");
        Socket connectionSocket = initSocket.accept();

        while (true) {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            sentence = inFromClient.readLine();

            if (sentence.equalsIgnoreCase("quit"))
                break;
            System.out.println("FROM CLIENT: " + sentence);
            System.out.print("Please type your text: ");

            userTxt = inFromUser.readLine();
            outToClient.writeBytes(userTxt + '\n');
            if (userTxt.equalsIgnoreCase("quit"))
                break;
        }

            //sentence = inFromClient.readLine();
            //System.out.println("FROM CLIENT: " + sentence);
        System.out.println("Shutting down..");
        connectionSocket.close();
            initSocket.close();
    }
}
