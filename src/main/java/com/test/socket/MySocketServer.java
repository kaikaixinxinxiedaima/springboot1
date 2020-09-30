package com.test.socket;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * socket
 * BIO 方式
 */
public class MySocketServer {

    public void publisher(Object server, int port){
        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            serverSocket = new ServerSocket(port);//监听端口

            while (true){
                //收到客户端请求，没有请求处于阻塞状态
                Socket socket = serverSocket.accept();
                executorService.execute(new SocketHandler(socket, server));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class SocketHandler implements Runnable{
        Socket socket;
        Object server;

        public SocketHandler(Socket socket, Object server) {
            this.socket = socket;
            this.server = server;
        }

        @Override
        public void run() {
            //处理客户端请求
            //启动释放资源
            try(
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ) {

                //读取客户端数据
                String readObject = (String) inputStream.readObject();
                String result = invoke(readObject);
                outputStream.writeObject(result);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public String invoke(String str){
        return "你好："+str;
    }

    public static void main(String[] args) {
        new MySocketServer().publisher(null,8888);
    }
}
