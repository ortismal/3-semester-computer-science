import java.io.*;
import java.net.*;

class TCPClient_test {
    public static void main(String[] args) throws Exception {

        System.out.println("starting TCPClient main");
        String sentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("trying to connect");
        Socket clientSocket = new Socket("10.111.176.43", 5656);
        System.out.println("we are connected");

        while (true) {

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("ClIENT: ");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');

            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }
            sentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + sentence);

            if (sentence.equalsIgnoreCase("quit")) {
                break;
            }

        }

        clientSocket.close();


    }

}
