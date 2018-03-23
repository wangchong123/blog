package com.wangchong.blog.thread;

import org.springframework.web.HttpRequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);
    static String basePath;
    static ServerSocket serverSocket;
    static int port = 8080;

    public static void setPort(int port){
        if(port > 0){
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath){
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start(){

        try {
            serverSocket = new ServerSocket(SimpleHttpServer.port);
            Socket socket = null;
           // System.out.println(socket == null);
            while((socket = serverSocket.accept()) != null){
            threadPool.execute(new HttpRequestHandler(socket));
              }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    static class HttpRequestHandler implements  Runnable{

        private Socket socket;

        public HttpRequestHandler(Socket socket){
            this.socket = socket;

        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    int  i = 0;
                    while((i = in.read()) != -1){
                        b.write(i);
                    }
                    byte[] array = b.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array,0,array.length);
                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Content-Type: text/html;charset=UTF-8");
                    out.println("");
                    while((line = br.readLine()) != null){
                        out.println(line);
                    }
                }
                out.flush();

            }catch (Exception e){
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally {
                close(br,in,reader,out,socket);

            }


        }
    }

    private static void close(Closeable... closeables){
        if(closeables != null){
            for(Closeable closeable : closeables){
                try {
                    if(closeable != null){
                        closeable.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        try {
            SimpleHttpServer.setPort(8888);
            SimpleHttpServer.setBasePath("D:\\file\\cms\\201708/1.html");
            SimpleHttpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
