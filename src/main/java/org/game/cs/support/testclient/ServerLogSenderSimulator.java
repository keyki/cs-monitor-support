package org.game.cs.support.testclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;

public class ServerLogSenderSimulator {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress("localhost", 43139));
        tempEvent(socket);
    }

    private static void tempEvent(DatagramSocket socket) throws IOException {
        for (int i = 0; i < 20; i++) {
            try {
                byte[] bytes = new Date().toString().getBytes();
                socket.send(new DatagramPacket(bytes, bytes.length, new InetSocketAddress("localhost", 5556)));
                System.out.println("sending");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
