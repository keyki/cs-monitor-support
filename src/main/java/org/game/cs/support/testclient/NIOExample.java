package org.game.cs.support.testclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NIOExample {
    
    public static void main(String[] args) throws IOException {
        
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(5556));
        channel.configureBlocking(false);
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        
        channel.receive(byteBuffer);
                
    }
    

}
