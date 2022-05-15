package client;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.start();

        Scanner scanner = new Scanner(System.in);

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
//                    System.out.println("Exiting ");
//                    System.exit(0);
//                    break;
//            }
//        }

    }
}
