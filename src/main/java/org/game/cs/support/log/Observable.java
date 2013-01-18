package org.game.cs.support.log;

import org.game.cs.support.log.event.LogEvent;

public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(LogEvent event);

}
