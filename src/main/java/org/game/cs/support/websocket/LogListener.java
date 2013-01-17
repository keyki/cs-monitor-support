package org.game.cs.support.websocket;

import java.util.Date;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.deploy.Verticle;

public class LogListener extends Verticle {

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.websocketHandler(new Handler<ServerWebSocket>() {
            public void handle(final ServerWebSocket ws) {

                if (ws.path.startsWith("/websocket")) {
                    for (int i = 0; i < 10; i++) {
                        ws.writeTextFrame(new Date().toString());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ws.close();
                } else {
                    ws.reject();
                }
            }
        }).listen(5555);

    }
}
