package Webserver;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.*;

public class WebServer_serial {
    static Thread client;

    public static void main(String[] args) {
        System.out.println("OK, we are starting the WebServer.");

        //opret socket
        try {
            ServerSocket listnerSocket = new ServerSocket(42424);
            System.out.println("OK, we have a listening socket.");

            //accepter socket og start ServiceTheClient metoden
            while (true) {
                Socket newsocket = listnerSocket.accept();
                System.out.println("OK, we got a client connection!");
                ServiceTheClient(newsocket);
            }

        } catch (IOException e) {
            System.out.println("Webserver IO exception");
        }

    }


    public static void ServiceTheClient(Socket con) {

        //Start ny thread for hver ny client.
        client = new Thread(() -> {
            Socket socket;
            socket = con;

            try {
                System.out.println("****************************************************************************");
                System.out.println("OK, we are starting to service the client.");
                String path = "\\Users\\patri\\Desktop\\3-semester-computer-science\\Patrick\\TCPUPD\\src\\Webserver\\";
                String requestMessageLine;
                String fileName;

                //Opret scanner og læs hvad client gerne vil ind på.
                Scanner inFromClient = new Scanner(socket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                requestMessageLine = inFromClient.nextLine();
                System.out.println("From Client:   " + requestMessageLine);

                StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

                //sørg for at det faktisk er en GET-request
                if (tokenizedLine.nextToken().equals("GET")) {
                    fileName = tokenizedLine.nextToken();

                    if (fileName.startsWith("/") == true) {
                        fileName = path + fileName;
                    }
                    //returner index ved start af program.
                    if (fileName.endsWith("/") == true) {
                        fileName = fileName + "index.html";
                    }

                    File file = new File(fileName);
                    //hvis fil ikke findes, redirect til error404.html
                    if (!file.isFile()) {
                        fileName = path + "error404.html";
                        file = new File(fileName);
                    }

                    //finder filen og byte længde.
                    System.out.println("Trying to find file: " + fileName);
                    int numOfBytes = (int) file.length();
                    FileInputStream inFile = new FileInputStream(fileName);
                    byte[] fileInBytes = new byte[numOfBytes];

                    outToClient.writeBytes("HTTP/1.0 200: Good request\r\n");
                    outToClient.writeBytes("Date: " + LocalDateTime.now() + "\r\n");
                    outToClient.writeBytes("Server: Patrick\r\n");
                    outToClient.writeBytes("Content-Type: " + fileName.substring(fileName.lastIndexOf(".")));
                    outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
                    outToClient.writeBytes("\r\n");

                    //sender 16KB ad gangen istedet for det hele på 1 gang
                    int off = 0;
                    while (numOfBytes > off + 16000) {
                        int readBytes = inFile.read(fileInBytes, off, 16000);
                        outToClient.write(fileInBytes, off, 16000);
                        System.out.println(off);
                        off += readBytes;
                    }
                    //sender de resterende bytes
                    System.out.println("Off size: " + off);
                    inFile.read(fileInBytes, off, numOfBytes - off);
                    inFile.close();//***** remember to close the file after usage *****
                    outToClient.write(fileInBytes, off, numOfBytes - off);
                    outToClient.writeBytes("\n");


                    System.out.println("OK, the file is sent to Client.");
                    System.out.println("****************************************************************************");

                    socket.close();
                }
                //error 500 Bad request
                else // no "GET"
                {
                    System.out.println("Bad request Message");
                    outToClient.writeBytes("HTTP/1.0 ERROR 500: NO GET REQUEST\r\n");
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



