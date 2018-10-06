import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client{

    private String name;
    private Socket s;
    private InputStream input;
    private OutputStream output;

    public Client(String name, Socket s, InputStream input, OutputStream output) {
        this.name = name;
        this.s = s;
        this.input = input;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}