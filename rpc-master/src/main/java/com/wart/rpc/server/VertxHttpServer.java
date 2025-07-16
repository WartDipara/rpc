package com.wart.rpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {
  
  @Override
  public void doStart(int port) {
    Vertx vertx = Vertx.vertx();
    
    //create http server
    io.vertx.core.http.HttpServer server = vertx.createHttpServer();
    
    //request handle
    server.requestHandler(
        httpServerRequest -> {
          // handle request
          System.out.println("Received request: " + httpServerRequest.method() + " " + httpServerRequest.uri());
          //send http response
          httpServerRequest.response()
              .putHeader("content-type", "text/plain")
              .end("Hello from Vert.x HTTP server.");
        }
    );
    
    //start server and listening port
    server.listen(port,
        httpServerAsyncResult -> {
          if (httpServerAsyncResult.succeeded()) {
            System.out.println("HTTP server started on port " + port);
          } else {
            System.out.println("Failed to start HTTP server: " + httpServerAsyncResult.cause());
          }
        }
    );
  }
}
