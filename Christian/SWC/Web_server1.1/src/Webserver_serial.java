import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

public class Webserver_serial
{

    public static void main(String[] args)
    {
        System.out.println("OK, we are starting the WebServer.");

        try
        {
//            InetAddress addr = InetAddress.getByName("127.0.0.1");
//            ServerSocket listnerSocket = new ServerSocket(42424, 50, addr);

            ServerSocket listnerSocket = new ServerSocket(42424);
            System.out.println("OK, we have a listening socket.");

            while (true) {
                Socket newsocket = listnerSocket.accept();
//                Thread T = new Thread(() -> {
                    System.out.println("OK, we got a client connection!");
                    ServiceTheClient(newsocket);
//                });
//                T.start();
            }

        }
        catch(IOException e)
        {
            System.out.println("Webserver IO exception");
        }

    }


    public static void ServiceTheClient(Socket con)
    {
        Thread T = new Thread(()->{
        Socket socket;
        socket = con;

        try
        {
            System.out.println("****************************************************************************");
            System.out.println("OK, we are starting to service the client.");
            String path = "/Users/Bruger/IdeaProjects/Web_server1.1/WebServerContent";
            String requestMessageLine;
            String fileName;

            //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner inFromClient = new Scanner(socket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            requestMessageLine = inFromClient.nextLine();
            System.out.println("From Client:   " + requestMessageLine);

            StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

            if(tokenizedLine.nextToken().equals("GET"))
            {
                fileName = tokenizedLine.nextToken();

                if(fileName.startsWith("/") == true)
                {
                    fileName = path + fileName;
                }

                if(fileName.endsWith("/") == true)
                {
                    fileName = fileName + "index.html";
                }

                File file = new File(fileName);
                if (!file.isFile())
                {
                    fileName = path + "/err404.html";
                    file = new File(fileName);
                }

                System.out.println("Trying to find file: " + fileName);

                int numOfBytes = (int) file.length();
                FileInputStream inFile = new FileInputStream(fileName);
                byte[] fileInBytes = new byte[numOfBytes];

                outToClient.writeBytes("HTTP/1.0 200 GET\r\n");

                if(fileName.endsWith(".jpg"))
                {
                    outToClient.writeBytes("Content-Type:image/jpeg\r\n");
                }

                if(fileName.endsWith(".gif"))
                {
                    outToClient.writeBytes("Content-Type:image/gif\r\n");
                }
                if (fileName.endsWith(".txt")) {
                    outToClient.writeBytes("Content-Type:text/txt\r\n");
                }
                if (fileName.endsWith(".docx")) {
                    outToClient.writeBytes("Content-Type:application/vnd.openxmlformats-officedocument.wordprocessingml.document\r\n");
                }
                if (fileName.endsWith(".png")){
                    outToClient.writeBytes("Content-Type:image/png");
                }
                if (fileName.endsWith(".mpeg")){
                    outToClient.writeBytes("Content-Type:video/mpeg");
                }
                if (fileName.endsWith(".png")){
                    outToClient.writeBytes("Content-Type:image/png");
                }
                if (fileName.endsWith(".ico")){
                    outToClient.writeBytes("Content-Type:image/x-icon");
                }
                if (fileName.endsWith(".html")){
                    outToClient.writeBytes("Content-Type:text/html");
                }
                if (fileName.endsWith(".mp4")){
                    outToClient.writeBytes("Content-Type:video/mp4");
                }
                if (fileName.endsWith(".*")){
                    outToClient.writeBytes("Content-Type:application/octet-stream");
                }

                outToClient.writeBytes("Date: " + LocalDate.now() + "\r\n");
                outToClient.writeBytes("Server: Christian Strunge" + "\r\n");
                outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
                outToClient.writeBytes("\r\n");
//                outToClient.write(fileInBytes, 0, numOfBytes);
//                outToClient.writeBytes("\r\n");
                int offSet = 0;

                // Read and write bytes in loop if bytes is bigger than off + 16kB
                while (numOfBytes > offSet + 16000) {
                    // Read max. 16kB a time and write those bytes to client
                    int bytes = inFile.read(fileInBytes, offSet, 16000);
                    outToClient.write(fileInBytes, offSet, 16000);
                    System.out.println("Temp:" + offSet);
                    offSet += bytes;
                }
                // Read remaining bytes (numOfBytes-off) and write to client
                // Outside of while-loop to prevent outOfBounds len > b.length
                System.out.println("Finally: " + offSet);
                inFile.read(fileInBytes, offSet, numOfBytes - offSet);
                outToClient.write(fileInBytes, offSet, numOfBytes - offSet);
                System.out.println("Final bytesize: " + numOfBytes); //Remainder of bytes
                inFile.close();
                outToClient.writeBytes("\n");


                System.out.println("OK, the file is sent to Client.");
                System.out.println("****************************************************************************");

                socket.close();
            }
            else // no "GET"
            {
                System.out.println("Bad request Message");
                outToClient.writeBytes("HTTP/1.0 500 Bad request\r\n");
                File err500 = new File("err500.html");
                FileInputStream inFile = new FileInputStream(path + "/err500.html");
                int numOfBytes = (int) err500.length();
                byte[] fileInBytes = new byte[numOfBytes];
                inFile.read(fileInBytes);
                outToClient.write(fileInBytes, 0, numOfBytes);
                outToClient.writeBytes("\n");
                socket.close();
            }
        }

        catch(IOException e)
        {
            System.out.println("IO Exception");
        }
        });
        T.start();

    }  // end of


}

