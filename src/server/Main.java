package server;


public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        System.out.println("Server started!");
        server.start();

        System.out.println("Received: " + server.readInput());
        System.out.println("Sent: " + server.send("A record # 12 was sent!"));

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
