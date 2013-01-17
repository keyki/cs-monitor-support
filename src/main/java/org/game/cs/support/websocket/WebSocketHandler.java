package org.game.cs.support.websocket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.ServerWebSocket;

public class WebSocketHandler implements Handler<ServerWebSocket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);
    private Map<String, ServerWebSocket> listeners = new ConcurrentHashMap<>();

    @Override
    public void handle(ServerWebSocket event) {
        if (isItGoodPath(event)) {
            registerListener(getServerAddress(event.path), event);
            tempEvent(event);
        } else {
            rejectConnection(event);
        }
    }

    private void rejectConnection(ServerWebSocket event) {
        event.reject();
        LOGGER.info("connection rejected");
    }

    private boolean isItGoodPath(ServerWebSocket event) {
        return event.path.startsWith("/websocket");
    }

    private void registerListener(String address, ServerWebSocket event) {
        listeners.put(address, event);
        LOGGER.info(address + " listener added");
    }

    private String getServerAddress(String path) {
        return path.split("/")[2];
    }

    private void tempEvent(ServerWebSocket event) {
        for (int i = 0; i < 20; i++) {
            event.writeTextFrame(new Date().toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
