package server;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Server {
   volatile Map<String, String> database;
    String address;
    int port;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    Gson gson;
   volatile File db;

    public Server() {
        address = "127.0.0.1";
        port = 22222;
        try {
            serverSocket = new ServerSocket(port, 50, InetAddress.getByName(address));
            gson = new Gson();
            Path path = Path.of("./JSON Database/task/src/server/data/db.json");
            db = new File(String.valueOf(path));
            database = readDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> readDataBase() {
        Map<String, String> temp = new HashMap<>();
        if (db.isFile()) {
            try {
                String  Json = String.join("", Files.readAllLines(db.toPath()));
                temp = gson.fromJson(Json, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return temp;
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
        updateDB();
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
        updateDB();
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

    void updateDB() {
       db.delete();
        try {
            db.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String out = gson.toJson(database);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(db));
            writer.write(out);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
