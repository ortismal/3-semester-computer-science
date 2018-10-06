package SWCUDPTCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String name;
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    public Client(String name, Socket socket, InputStream input, OutputStream output) {
        this.name = name;
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", s=" + socket +
                ", input=" + input +
                ", output=" + output +
                '}';
    }
}
