package org.ACRusher.leetcode;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @author xiliang.zxl
 * @date 2015-12-20 下午11:46
 */
public class CharBufferTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        URLClassLoader urlClassLoader=new URLClassLoader(new URL[]{new URL("file:/tmp/")});
        urlClassLoader.loadClass("Bulbs");

        File file=new File("/tmp");
        file.toURI().toURL();

        FileChannel fcin=new FileInputStream("/tmp/test.txt").getChannel();
        FileChannel fcout=new FileOutputStream("/tmp/test1.txt").getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        while (true){
            byteBuffer.clear();
            int cnt=fcin.read(byteBuffer);
            if(cnt==-1) break;
            byteBuffer.flip();
            fcout.write(byteBuffer);
        }


    }

}
