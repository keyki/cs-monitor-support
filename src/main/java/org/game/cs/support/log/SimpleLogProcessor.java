package org.game.cs.support.log;

import org.game.cs.support.log.event.ChatLogEvent;
import org.game.cs.support.log.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogProcessor implements LogProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleLogProcessor.class);

    //����RL 01/21/2013 - 15:46:04: "Console<0>" say "alma" dress_add fsanyee.no-ip.org:5556" ke")
    @Override
    public LogEvent process(String sender, String logMessage) {
        LOGGER.info("received from: " + sender + " message: " + logMessage);
        String[] split = logMessage.split("\"");
        if (split[2].trim().equals("say")) {
            return new ChatLogEvent(sender, split[1] + " : " + split[3]);
        }
        return null;
    }

}
