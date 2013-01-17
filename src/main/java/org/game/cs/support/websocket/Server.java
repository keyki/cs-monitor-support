package org.game.cs.support.websocket;

import org.vertx.java.core.http.HttpServer;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {

    private WebSocketHandler socketHandler;
    private int websocketPort = 5555;

    public Server() {
        socketHandler = new WebSocketHandler();
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.websocketHandler(socketHandler).listen(websocketPort);

    }
}
