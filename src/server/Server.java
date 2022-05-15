package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    Map<String, String> database;
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
        database = new HashMap<>();
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

    public Response setData(String key, String data) {
        database.put(key, data);
        Response response = new Response();
        response.setResponse("OK");
        return response;
    }

    public Response getData(String key) {
        String value = database.get(key);
        Response response = new Response();
        if (value != null) {
            response.setResponse("OK");
            response.setValue(value);
        } else {
            response.setResponse("ERROR");
            response.setReason("No such key");
        }
        return response;
    }

    public Response deleteData(String key) {
        Response response = new Response();
       String value = database.remove(key);
        if (value != null) {
            response.setResponse("OK");
        } else {
            response.setResponse("ERROR");
            response.setReason("No such key");
        }
        return response;
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
