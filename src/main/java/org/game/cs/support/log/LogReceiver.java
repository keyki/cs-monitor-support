package org.game.cs.support.log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;

import org.game.cs.support.log.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogReceiver implements Observable, Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogReceiver.class);
    private static final int UDPLOGPORT = 5556;
    private volatile boolean exit;
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    private Collection<Observer> observers;
    private LogProcessor logProcessor;

    public LogReceiver() throws SocketException {
        init();
    }

    @Override
    public void registerObserver(Observer observer) {
        LOGGER.info("registering observer " + observer);
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        LOGGER.info("removing observer " + observer);
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(LogEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    private void init() throws SocketException {
        createObserversList();
        createLogProcessor();
        createSocket();
        createPacket();
    }

    private void createLogProcessor() {
        logProcessor = new SimpleLogProcessor();
    }

    private void createObserversList() {
        observers = new ArrayList<>();
    }

    private void createPacket() {
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
    }

    private void createSocket() throws SocketException {
        serverSocket = new DatagramSocket(UDPLOGPORT);
        LOGGER.info("socket created on port: " + UDPLOGPORT);
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                DatagramPacket packet = reveive();
                notifyObservers(logProcessor.process(getSender(packet), getMessage(packet)));
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private String getMessage(DatagramPacket packet) {
        return new String(packet.getData());
    }

    private String getSender(DatagramPacket packet) {
        return packet.getAddress().getHostAddress() + ":27015";
    }

    private DatagramPacket reveive() throws IOException {
        serverSocket.receive(receivePacket);
        return receivePacket;
    }

}
