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
    	CharsetDecoder decoder = charset.newDecoder();//Constructs a new decoder for this charset.
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("E:/学习总结/java乱码本质.txt");
            FileChannel fileChannel = fis.getChannel();//Returns the unique FileChannel object associated with this file input stream. 
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//Allocates a new byte buffer. 
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            int bytes = fileChannel.read(byteBuffer);//Reads a sequence of bytes from this channel into the given buffer. 
            while(bytes!=-1){
                byteBuffer.flip();//Flips this buffer. The limit is set to the current position and then the position is set to zero. If the mark is defined then it is discarded.
                decoder.decode(byteBuffer, charBuffer, false);//Decodes as many bytes as possible from the given input buffer, writing the results to the given output buffer. 
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