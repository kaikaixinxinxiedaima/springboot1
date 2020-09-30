package com.test.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {


    public Object sendRequest(String str){

        try (Socket socket = new Socket("127.0.0.1",8888);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){

            outputStream.writeObject(str);
            outputStream.flush();
            return inputStream.readObject();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        String s = (String) new MyClient().sendRequest("张三");
        System.out.println(s);
    }
}
