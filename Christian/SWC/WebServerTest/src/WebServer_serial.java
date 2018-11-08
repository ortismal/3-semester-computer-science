import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class WebServer_serial {

    public WebServer_serial() {
    }

    public static void main(String[] args) {
        System.out.println("OK, we are starting the WebServer.");

        try {
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            ServerSocket listnerSocket = new ServerSocket(42424, 50, addr);
            System.out.println("OK, we have a listening socket.");

            while (true) {
                Socket newsocket = listnerSocket.accept();
                Thread T = new Thread(() -> {
                    System.out.println("OK, we got a client connection!");
                    ServiceTheClient(newsocket);
                });
                T.start();
            }

        } catch (IOException e) {
            System.out.println("Webserver IO exception");
        }

    }


    public static void ServiceTheClient(Socket con) {
        Socket socket;
        socket = con;

        try {
            System.out.println("****************************************************************************");
            System.out.println("OK, we are starting to service the client.");
            String path = "C:/Users/Bruger/Desktop/WebServerContent";
            String requestMessageLine;
            String fileName;

            //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner inFromClient = new Scanner(socket.getInputStream());
            OutputStream outToClient = socket.getOutputStream();
            OutputStream outToFile = socket.getOutputStream();

//            OutputStream outToClient = new OutputStream(socket.getOutputStream());
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
                    fileName = path + "/error404.html";
                    file = new File(fileName);
                }

                System.out.println("Trying to find file: " + fileName);

                int numOfBytes = (int) file.length();



                byte[] fileInBytes = new byte[numOfBytes];
                BufferedInputStream inputFile = new BufferedInputStream(new FileInputStream(fileName));
                //BufferedOutputStream out = new BufferedOutputStream(new FileoutputStream("toFile"));
                int off = 0;
                // Read and write bytes in loop if bytes is bigger than off + 16kB
                while (numOfBytes > off + 16000) {
                    // Read max. 16kB a time and write those bytes to client
                    int bytesRead = inputFile.read(fileInBytes, off, 16000);
                    outToClient.write(fileInBytes, off, 16000);
                    System.out.println(off);  // Test print
                    // Increment off
                    off += bytesRead;
                }
                // Read remaining bytes (numOfBytes-off) and write to client
                // Outside of while-loop to prevent outOfBounds len > b.length
                System.out.println("OFF: " + off); // Test print
                inputFile.read(fileInBytes, off, numOfBytes-off);
                outToClient.write(fileInBytes, off, numOfBytes-off);
                inputFile.close();  //***** remember to close the file after usage *****
                outToClient.write(off);
                inputFile.close();
                outToFile.close();


//                int count = 0;
//                int antalPakker = (int) (file.length() / fileInBytes.length);
//                while (count != antalPakker) {
//
//                    try{
//                        fileInBytes = inputFile.readNBytes(16_000);
//                        byte[] t = inputFile[count];
//                        outToFile.write();
//                        count++;
//                    } catch (IOException e){
////                        outToClient.write("HTTP/1.0 - ERROR: 500\r\n");
////                        outToClient.writeBytes("\n");
//                        socket.close();
//                    }
//                }
//
//                outToFile.close();

//                int pakkeStoerelse = 16000;
//                double antalPakker = Math.ceil(((int)file.length()) / pakkeStorelse);
//                BufferedInputStream bis =
//                        new BufferedInputStream(new FileInputStream(file));
//
//                for (double i = 0; i < antalPakker + 1; i++) {
//                    byte[] fileByteArray = new byte[pakkeStorelse];
//                    bis.read(fileByteArray, 0, fileByteArray.length);
//                    System.out.println("Pakke: " + (i + 1));
//                    OutputStream os = sock.getOutputStream();
//                    os.write(fileByteArray, 0, fileByteArray.length);
//                    os.flush();
//                }

                inputFile.close();  //***** remember to close the file after usage *****

                String LocalDateTime_ = "HTTP/1.0 200 " + LocalDateTime.now() + " Server: Christian Strunge " + "\r\n";
                outToClient.write(LocalDateTime_.getBytes());

                if (fileName.endsWith(".jpg")) {
                    outToClient.write("Content-Type:image/jpeg\r\n".getBytes());
                }

                if (fileName.endsWith(".gif")) {
                    outToClient.write("Content-Type:image/gif\r\n".getBytes());
                }
                if (fileName.endsWith(".txt")) {
                    outToClient.write("Content-Type:text/txt\r\n".getBytes());
                }
                if (fileName.endsWith(".docx")) {
                    outToClient.write("Content-Type:application/vnd.openxmlformats-officedocument.wordprocessingml.document\r\n".getBytes());
                }
                if (fileName.endsWith(".png")){
                    outToClient.write("Content-Type:image/png".getBytes());
                }
                if (fileName.endsWith(".mpeg")){
                    outToClient.write("Content-Type:video/mpeg".getBytes());
                }
                if (fileName.endsWith(".png")){
                    outToClient.write("Content-Type:image/png".getBytes());
                }
                if (fileName.endsWith(".ico")){
                    outToClient.write("Content-Type:image/x-icon".getBytes());
                }
                if (fileName.endsWith(".html")){
                    outToClient.write("Content-Type:text/html".getBytes());
                }
                if (fileName.endsWith(".mp4")){
                    outToClient.write("Content-Type:video/mp4".getBytes());
                }
                if (fileName.endsWith(".*")){
                    outToClient.write("Content-Type:application/octet-stream".getBytes());
                }

                String str = "Content-Length: " + numOfBytes + "\r\n";
                outToClient.write(str.getBytes());
                outToClient.write("\r\n".getBytes());
                outToClient.write(fileInBytes, 0, numOfBytes);
                outToClient.write("\n".getBytes());

                System.out.println("OK, the file is sent to Client.");
                System.out.println("****************************************************************************");

                socket.close();
            } else // no "GET"
            {
                System.out.println("Bad request Message");
                outToClient.write("HTTP/1.0 - ERROR: 500\r\n".getBytes());
                outToClient.write("\n".getBytes());
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

    }  // end of


}