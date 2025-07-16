package com.wart.provider;

import com.wart.rpc.server.HttpServer;
import com.wart.rpc.server.VertxHttpServer;

public class ProviderMain {
  public static void main(String[] args) {
    HttpServer httpServer = new VertxHttpServer();
    httpServer.doStart(8080);
  }
}
