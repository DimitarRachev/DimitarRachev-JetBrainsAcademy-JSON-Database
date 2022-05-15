package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    String[] database;
    String address;
    int port;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;

    public Server() {
        address = "127.0.0.1";
        port = 22222;
        try {
            serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
        } catch (IOException e) {
            e.printStackTrace();
        }
        database = new String[1000];
    }

    String start() {
        try {
            socket = serverSocket.accept();
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            return "Server started!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean indexIsValid(int index) {
        return index >= 0 && index < database.length;
    }

    public String readInput() {
        try {
            return input.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String send(String s) {
        try {
            output.writeUTF(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String setData(int index, String data) {
        if (indexIsValid(index)) {
            database[index] = data;
            return "OK";
        }
        return "ERROR";
    }

    public String getData(int index) {
        if (indexIsValid(index) && database[index] != null) {
            return database[index];
        }
        return "ERROR";
    }

    public String deleteData(int index) {
        if (indexIsValid(index)) {
            database[index] = null;
            return "OK";
        }
        return "ERROR";
    }

    public String shutdown() {
        try {
            serverSocket.close();
            socket.close();
            input.close();
            output.close();
            return "Exiting";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
