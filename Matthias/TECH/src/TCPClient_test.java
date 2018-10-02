import java.io.*;
        import java.net.*;

class TCPClient_test
{
    public static void main(String argv[]) throws Exception
    {
        System.out.println("starting TCPClient main");
        String sentence;
        boolean isQuit = true;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("trying to connect");
        Socket clientSocket = new Socket("127.0.0.1", 5656);
        System.out.println("we are connected");

        while(isQuit) {

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("Please type your text: ");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            if(sentence.equalsIgnoreCase("quit") || sentence.equalsIgnoreCase("quit" )){
                System.out.println("Done!");
                isQuit = false;
            }

            sentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + sentence);
            if(sentence.equalsIgnoreCase("quit") || sentence.equalsIgnoreCase("quit" )){
                System.out.println("Done!");
                isQuit = false;
            }

        }

        clientSocket.close();

    }

}