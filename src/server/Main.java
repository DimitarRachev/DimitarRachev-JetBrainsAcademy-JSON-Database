package server;


import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();
        Server server = new Server();
        System.out.println("Server started!");
        while (true) {
            server.start();

            Request request = gson.fromJson(server.readInput(), Request.class);
            String key = request.getKey();
            switch (request.getType()) {
                case "get":
                    server.send(gson.toJson(server.getData(key)));
                    break;
                case "set":
                    String data = request.getValue();
                    server.send(gson.toJson(server.setData(key, data)));
                    break;
                case "delete":
                    server.send(gson.toJson(server.deleteData(key)));
                    break;
                case "exit":
                    server.shutdown();
                    System.exit(0);
                    break;
            }
        }
//        while (!"exit".equals(line)) {
//            String[] input = line.split("\\s+");
//            String command = input[0];
//            int index = Integer.parseInt(input[1]) - 1;
//            if (!server.indexIsValid(index)) {
//                System.out.println("ERROR");
//                continue;
//            }
//            switch (command) {
//                case "set":
//                    String data = line.substring(line.indexOf(input[1]) + input[1].length());
//                    server.setData(index, data);
//                    System.out.println(server.send("OK"));
//                    break;
//                case "get":
//                    if (server.getData(index) == null) {
//                        System.out.println(server.send("ERROR"));
//                    } else {
//                        System.out.println(server.send(server.getData(index)));
//                    }
//                    break;
//                case "delete":
//                    System.out.println(server.deleteData(index));
//                    break;
//                case "exit":
//                    System.out.println(server.shutdown());
//                    System.exit(0);
//            }
//        }
    }


}
