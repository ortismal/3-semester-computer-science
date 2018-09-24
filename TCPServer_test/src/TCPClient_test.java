import java.io.*;
import java.net.*;

class TCPClient_test {
    public static void clientConnection() throws Exception {
        System.out.println("starting TCPClient main");
        String sentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("trying to connect");
        Socket clientSocket = new Socket("10.111.184.45", 5656);
        System.out.println("we are connected");

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Please type your text: ");
        sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');

        sentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + sentence);

        clientSocket.close();

    }

}
