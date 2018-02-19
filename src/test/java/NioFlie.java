import jdk.internal.org.objectweb.asm.Handle;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;

import javax.ws.rs.Path;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by 陈源熹 on 2018-01-23.
 */
public class NioFlie {

    public NioFlie(){};

    @Test
    public void NioFlieTest() throws Exception{
        long currentTime = System.currentTimeMillis();
        RandomAccessFile aFile=new RandomAccessFile("C:\\迅雷游戏\\rs\\CuseGKC.mp4","rw");
        RandomAccessFile aFile2=new RandomAccessFile("C:/Users/陈源熹/Desktop/nio/cc.mp4","rw");
        FileChannel channel = aFile.getChannel();
        FileChannel channel2 = aFile2.getChannel();

//        channel.transferTo(0,channel.size(),channel2);

//        Charset charset = Charset.forName("UTF-8");
//        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
//        CharBuffer charBuffer = CharBuffer.allocate(1024);
        int bytesRead=channel.read(byteBuffer);
        while (bytesRead!=-1){
            byteBuffer.flip();
//            decoder.decode(byteBuffer, charBuffer, false);
//            charBuffer.flip(); // 切换到读模式
            while (byteBuffer.hasRemaining()){
//              System.out.print( charBuffer.get());
                channel2.write(byteBuffer);
            }
//            charBuffer.clear();
            byteBuffer.clear();
            bytesRead=channel.read(byteBuffer);
        }
        long currentTime2 = System.currentTimeMillis();
        aFile.close();
        aFile2.close();
        System.out.print("测试时间："+((currentTime2-currentTime)/60)/60+"ms");

    }

    @Test
    public void nioDate() throws IOException {

        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        ByteBuffer buf=ByteBuffer.allocate(1024);
        RandomAccessFile aFile=new RandomAccessFile("C:\\迅雷游戏\\rs\\CuseGKC.mp4","rw");

        FileChannel channel = aFile.getChannel();
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();

            if(socketChannel !=null){
                String newData = "New String to write to file..." + System.currentTimeMillis();
                System.out.print("获取连接:"+newData);
                int bypt=channel.read(buf);
                if(bypt==-1)
                {
                   channel.close();
                }
                buf.flip();
                while (buf.hasRemaining()){
                    socketChannel.write(buf);
                    System.out.println("传送视频:"+newData);
                }
                socketChannel.close();
                break;
            }
        }
        serverSocketChannel.close();
    }



}
