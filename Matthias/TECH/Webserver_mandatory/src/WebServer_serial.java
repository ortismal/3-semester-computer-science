import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.*;

public class WebServer_serial
{

    public static void main(String[] args)
    {
        System.out.println("OK, we are starting the WebServer.");

        try
        {
            ServerSocket listnerSocket = new ServerSocket(42424);
            System.out.println("OK, we have a listening socket.");

            while(true)
            {
                Socket newsocket = listnerSocket.accept();
                System.out.println("OK, we got a client connection!");
                ServiceTheClient(newsocket);
            }

        }
        catch(IOException e)
        {
            System.out.println("Webserver IO exception");
        }

    }


    public static void ServiceTheClient(Socket con)
    {
       Thread client = new Thread(() -> {
            Socket socket;
            socket = con;

            try {
                System.out.println("****************************************************************************");
                System.out.println("OK, we are starting to service the client.");
                String path = "C:\\Users\\Matthias Skou\\Desktop\\GitHub 3. Semester\\3-semester-computer-science\\Matthias\\TECH\\Webserver_mandatory\\test\\";
                String requestMessageLine;
                String fileName;

                Scanner inFromClient = new Scanner(socket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                requestMessageLine = inFromClient.nextLine();
                System.out.println("From Client:   " + requestMessageLine);

                StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

                if (tokenizedLine.nextToken().equals("GET")) {
                    fileName = tokenizedLine.nextToken();

                    if (fileName.startsWith("/") == true) {
                        fileName = path + fileName;
                    }

                    if (fileName.endsWith("/") == true) {
                        fileName = fileName + "index.html";
                    }

                    File file = new File(fileName);
                    if (!file.isFile()) {
                        System.out.println("PATH IS : " + path);
                        fileName = path + "error404.html";
                        file = new File(fileName);
                    }

                    System.out.println("Trying to find file: " + fileName);

                    int numOfBytes = (int) file.length();
                    FileInputStream inFile = new FileInputStream(fileName);
                    byte[] fileInBytes = new byte[numOfBytes];
                    int off = 0;

                        //outToClient.writeBytes("\r\n");
                        while (numOfBytes > off + 16000) {
                            off += inFile.read(fileInBytes, off, 16000);
                            //outToClient.write(fileInBytes, off, 16000);
                            System.out.println(off);
                        }
                        //outToClient.write(fileInBytes, off, numOfBytes-off);
                        //outToClient.writeBytes("\n");
                    // else {
                        // Read rest of bytes or read entire if > 16000
                    inFile.read(fileInBytes, off, numOfBytes-off);
                    //}

                    inFile.close();  //***** remember to close the file after usage *****
                    outToClient.writeBytes("HTTP/1.0 200 GET Request\r\n");
                    outToClient.writeBytes("Date: " + LocalDate.now() + "\r\n");
                    outToClient.writeBytes("Server: Matthias Skou" + "\r\n");
                    outToClient.writeBytes("Content-Type: " + fileName.substring(fileName.lastIndexOf('.')));
                    outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
                    outToClient.writeBytes("\r\n");
                    outToClient.write(fileInBytes, 0, numOfBytes);
                    outToClient.writeBytes("\n");


                    System.out.println("OK, the file is sent to Client.");
                    System.out.println("****************************************************************************");

                    socket.close();
                } else // no "GET"
                {
                    System.out.println("Bad request Message");
                    outToClient.writeBytes("HTTP/1.0 500 Bad request \r\n");
                    File file = new File("error500.html");
                    FileInputStream inFile = new FileInputStream(path + "error500.html");
                    int numOfBytes = (int) file.length();
                    byte[] fileInBytes = new byte[numOfBytes];
                    inFile.read(fileInBytes);
                    outToClient.write(fileInBytes, 0, numOfBytes);
                    outToClient.writeBytes("\n");
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("IO Exception");
            }
       });
       client.start();
    }  // end of


}


