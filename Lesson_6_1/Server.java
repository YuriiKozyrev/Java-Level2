package Lesson_6_1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        ServerSocket server= null;
        Socket socket = null;

        try{
            server= new ServerSocket(8189);
            System.out.println("Сервер запущен");

            socket = server.accept();
            System.out.println("Клиент подключился");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);


            Thread inThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){
                        String str1 = null;
                        try {
                            str1 = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (str1.equals("/end")){
                            break;
                        }

                        System.out.println("От клиента: " + str1);
                    }
                }
            });

            Thread outThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            String str = sc.nextLine();
                            out.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            inThread.start();
            outThread.start();

            inThread.join();
            outThread.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
