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

//        while (true) {
//            String[] input = scanner.nextLine().split("\\s+");
//            String command = input[0];
//            int index = Integer.parseInt(input[1]) - 1;
//            if (!indexIsValid(index)) {
//                System.out.println("ERROR");
//                continue;
//            }
//            switch (command) {
//                case "set":
//                    String data = line.substring(line.indexOf(input[1]) + input[1].length());
//                    database[index] = data;
//                    System.out.println("OK");
//                    break;
//                case "get":
//                    if (database[index] == null) {
//                        System.out.println("ERROR");
//                    } else {
//                        System.out.println(database[index]);
//                    }
//                    break;
//                case "delete":
//                    database[index] = null;
//                    System.out.println("OK");
//                    break;
//                case "exit":
//                    client.sendData("exit");
////                    System.out.println("Exiting ");
////                    System.exit(0);
//                    break;
//            }
//        }

    }
}
