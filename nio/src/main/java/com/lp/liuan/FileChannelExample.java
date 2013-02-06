package com.lp.liuan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelExample {
    public static void main(String args[]){
    	Charset charset = Charset.forName("GBK");//Java.nio.charset.Charset处理了字符转换问题。它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
    	CharsetDecoder decoder = charset.newDecoder();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("E:/学习总结/java乱码本质.txt");
            FileChannel fileChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            int bytes = fileChannel.read(byteBuffer);
            while(bytes!=-1){
                byteBuffer.flip();
                decoder.decode(byteBuffer, charBuffer, false);
                charBuffer.flip();
              
                System.out.println(charBuffer);
                charBuffer.clear();
                byteBuffer.clear();
                bytes = fileChannel.read(byteBuffer);
            }
            if(fis!=null){
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}