package org.game.cs.support.log;

import org.game.cs.support.log.event.LogEvent;

public interface LogProcessor {
    
    LogEvent process(String sender, String logMessage);

}
