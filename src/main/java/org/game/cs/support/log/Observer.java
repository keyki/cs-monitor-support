package org.game.cs.support.log;

import org.game.cs.support.log.event.LogEvent;

public interface Observer {
    
    void update(LogEvent event);

}
