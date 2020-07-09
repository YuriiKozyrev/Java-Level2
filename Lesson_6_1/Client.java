package Lesson_6_1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        final String IP_ADRESS = "localhost";
        final int PORT = 8189;

        socket = new Socket(IP_ADRESS, PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Клиент запущен");

            Thread outThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String str = sc.nextLine();
                        if (str.equals("/end")){
                            break;
                        }
                        try {
                            out.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            Thread inThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String str = null;
                        try {
                            str = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("От сервера: "  + str);
                    }
                }
            });

            inThread.start();
            outThread.start();

            inThread.join();
            outThread.join();


        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
