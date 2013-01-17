package org.game.cs.support.log;

public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(LogEvent event);

}
