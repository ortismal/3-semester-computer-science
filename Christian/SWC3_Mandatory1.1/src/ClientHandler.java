import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private String name;
    private InputStream input;
    private OutputStream output;

    public ClientHandler(){

    }

    public ClientHandler(Socket socket, String name, InputStream input, OutputStream output) {
        this.socket = socket;
        this.name = name;
        this.input = input;
        this.output = output;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }
}
