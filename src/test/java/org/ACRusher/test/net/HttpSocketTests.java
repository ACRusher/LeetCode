package org.ACRusher.test.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiliang.zxl
 * @date 2016-05-02 下午12:16
 */
public class HttpSocketTests {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket=new ServerSocket(8080);
        while (true){
            Socket socket=serverSocket.accept();
            InputStream inputStream=socket.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream=socket.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));


            String line=null;
            String method=null;
            int spaceCnt=0;
            while ((line=bufferedReader.readLine())!=null) {
                if(method==null) method=line.split("\\s+")[0];
                System.out.println(line);
                if(line.trim().isEmpty()) spaceCnt++;
                if(method.equalsIgnoreCase("GET") && spaceCnt>0) break;
                if(spaceCnt==1){
//                    bufferedWriter.write("Content: application/json");
                    bufferedWriter.flush();
                }
                if(spaceCnt>=2) break;
            }
            System.out.println("read end.");
//            Thread.currentThread().sleep(10000);

            try {
                System.out.println("start>>>");
                bufferedWriter.write("HTTP/1.1 200 OK");
                bufferedWriter.newLine();
                System.out.println("here");
                bufferedWriter.write("Content: application/json");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                System.out.println("here2");
                bufferedWriter.write("{\"a\":1}");
                bufferedWriter.flush();
                System.out.println("here3");


//                while (!socket.isClosed()) {
//                    System.out.println(" still connecting ... ");
//                    Thread.currentThread().sleep(2000);
//                }
            }catch (Exception e){
                inputStream.close();
                outputStream.close();
                socket.close();
            }finally {

            }
            if(!socket.isClosed()) {
                inputStream.close();
                outputStream.close();
                socket.close();
            }

        }
    }
}
