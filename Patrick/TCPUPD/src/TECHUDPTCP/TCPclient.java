package TECHUDPTCP;

import java.io.*;
import java.net.*;

public class TCPclient {

    public static void main(String argv[]) throws Exception {
        System.out.println("starting TCPClient main");
        String sentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("trying to connect");
        Socket clientSocket = new Socket("127.0.0.1", 42424);
        System.out.println("we are connected");
        while (true) {

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("Please type your text: ");
            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');
            if(sentence.equalsIgnoreCase("quit"))
                break;
            sentence = inFromServer.readLine();
            if(sentence.equalsIgnoreCase("quit"))
                break;
            System.out.println("FROM SERVER: " + sentence);
        }

        System.out.println("Closing program");
        clientSocket.close();

    }

}
