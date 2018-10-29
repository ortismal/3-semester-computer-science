import java.io.*;
import java.net.*;

class TCPClient
{
    public static void main(String argv[]) throws Exception
    {
        System.out.println("starting TCPClient main");
        String sentence;
        String serverText;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("trying to connect");
        Socket clientSocket = new Socket("127.0.0.1", 5656);
        System.out.println("we are connected");

        do {

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("Please type your text: ");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            if(sentence.equals("quit")){
                System.out.println("Shutting down!");
                break;
            }

            serverText = inFromServer.readLine();
            System.out.println("FROM SERVER: " + serverText);

            if(serverText.equals("quit")){
                System.out.println("Shutting down!");
                break;
            }

        } while (true);

        clientSocket.close();
        System.exit(0);

    }

}