package org.game.cs.support.websocket;

import java.net.SocketException;

import org.game.cs.support.log.LogReceiver;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.deploy.Verticle;

public class Server extends Verticle {

    private WebSocketHandler socketHandler;
    private LogReceiver logReceiver;
    private static final int SERVERPORT = 5555;

    public Server() throws SocketException {
        socketHandler = new WebSocketHandler();
        logReceiver = new LogReceiver();
        logReceiver.registerObserver(socketHandler);
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.websocketHandler(socketHandler).listen(SERVERPORT);
        (new Thread(logReceiver)).start();
    }

}
