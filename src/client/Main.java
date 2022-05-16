package client;


import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
//      2 lines sending exit command for debugging
//        Request debug = new Request("exit", null, null);
//        System.out.println(client.sendData(new Gson().toJson(debug)));
        CommandParams commandParams = new CommandParams();
        JCommander.newBuilder().addObject(commandParams).build().parse(args);

        String forSending = "";
        String name = commandParams.getName();
        if (name == null) {
            String type = commandParams.getCommand();
            String key = commandParams.getKey();
            String data = commandParams.getData();

            Request request = new Request(type, key, data);

            forSending = new Gson().toJson(request);
            System.out.println("Sent: " + client.sendData(forSending));

        } else {
            try {
                Path path = Path.of("./src/client/data/" + name);
//                  2 lines for checking path
//                System.out.println(path.toAbsolutePath());
//                System.out.println(path.toFile().isFile());
                forSending = String.join("", Files.readAllLines(path));
                System.out.println("Sent: " + client.sendData(forSending));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Received: " + client.readData());
    }
}
