package org.game.cs.support.testclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServerLogSenderSimulator {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress("localhost", 43139));
        tempEvent(socket);
    }

    private static void tempEvent(DatagramSocket socket) throws IOException {
        for (int i = 0; i < 20; i++) {
            try {
                byte[] bytes = "15:56:45: \"Console<0>\" say \"pinaaaa\" ss_add fsanyee.no-ip.org:5556\" ke\") (version \"5066\") d 22 h".getBytes();
                socket.send(new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 5556)));
                System.out.println("sending");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
