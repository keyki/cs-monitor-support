package org.game.cs.support.log;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogReceiver implements Observable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogReceiver.class);
    private static final int UDPLOGPORT = 5556;
    private Collection<Observer> listeners;

    public LogReceiver() {
        listeners = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        LOGGER.info("registering observer " + observer);
        listeners.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        LOGGER.info("removing observer " + observer);
        listeners.remove(observer);
    }

    @Override
    public void notifyObservers(LogEvent event) {

    }

}
