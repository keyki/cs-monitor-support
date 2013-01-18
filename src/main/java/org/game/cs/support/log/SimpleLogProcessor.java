package org.game.cs.support.log;

import org.game.cs.support.log.event.ChatLogEvent;
import org.game.cs.support.log.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogProcessor implements LogProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLogProcessor.class);

    @Override
    public LogEvent process(String sender, String logMessage) {
        LOGGER.info("received from: " + sender + " message: " + logMessage);
        return new ChatLogEvent(sender, logMessage);
    }

}
