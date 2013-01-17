package org.game.cs.support.websocket;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.deploy.Verticle;

public class LogListener extends Verticle {

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        server.websocketHandler(new Handler<ServerWebSocket>() {
            public void handle(ServerWebSocket ws) {

                if (ws.path.startsWith("/websocket")) {
                    ws.writeTextFrame("alma");
                } else {
                    ws.reject();
                }
            }
        }).listen(5555);

    }

}
