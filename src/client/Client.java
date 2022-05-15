package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    String address;
    int port;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;

    public Client()  {
         address = "127.0.0.1";
         port = 22222;
        try {
            socket = new Socket(InetAddress.getByName(address), port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void start() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client started!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String readData() {
        try {
            return input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    String sendData(String s) {
        try {
            output.writeUTF(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
