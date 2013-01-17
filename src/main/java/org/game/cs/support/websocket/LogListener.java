package org.game.cs.support.websocket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.deploy.Verticle;

public class LogListener extends Verticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogListener.class);
    private Map<String, ServerWebSocket> listeners = new ConcurrentHashMap<>();
    private int websocketPort = 5555;
    private int csLogPort = 5556;

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.websocketHandler(new Handler<ServerWebSocket>() {
            public void handle(final ServerWebSocket ws) {
                if (ws.path.startsWith("/websocket")) {
                    String address = getServerAddress(ws.path);
                    LOGGER.info("adding " + address + " to listeners");
                    listeners.put(address, ws);
                    for (int i = 0; i < 20; i++) {
                        ws.writeTextFrame(new Date().toString());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ws.close();
                } else {
                    ws.reject();
                    LOGGER.info("ws rejected");
                }

            }

            private String getServerAddress(String path) {
                return path.split("/")[2];
            }
            
        }).listen(websocketPort);

    }
}
