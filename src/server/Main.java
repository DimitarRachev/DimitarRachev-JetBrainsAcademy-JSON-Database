package server;


import com.google.gson.Gson;

import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();
        ExecutorService executor = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors()));
        Server server = new Server();
        System.out.println("Server started!");
        while (true) {
            server.start();

            executor.submit(() -> {


                String json = server.readInput();
                System.out.println("Received: " + json);
                Request request = gson.fromJson(json, Request.class);
                String key = request.getKey();
                switch (request.getType()) {
                    case "get":
                        System.out.println("Sent: " + server.send(gson.toJson(server.getData(key))));
//                        server.send(gson.toJson(server.getData(key)));
                        break;
                    case "set":
                        String data = request.getValue();
                        System.out.println("Sent: " + server.send(gson.toJson(server.setData(key, data))));
//                        server.send(gson.toJson(server.setData(key, data)));
                        break;
                    case "delete":
                        System.out.println("Sent: " + server.send(gson.toJson(server.deleteData(key))));
//                        server.send(gson.toJson(server.deleteData(key)));
                        break;
                    case "exit":
//                        System.out.println("exit received");
                        executor.shutdown();
//                        System.out.println("Executor killed" );
                        System.out.println(server.send(gson.toJson(server.shutdown())));
//                        System.out.println("server killed");
                        Runtime.getRuntime().halt(0);
//                        System.out.println("After Halt");
                        break;
                }
            });
        }
    }
}
