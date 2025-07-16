package com.wart.rpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {
  
  /**
   * 啟動 Http服務
   * @param port
   */
  @Override
  public void doStart(int port) {
    Vertx vertx = Vertx.vertx();
    
    //create http server
    io.vertx.core.http.HttpServer server = vertx.createHttpServer();
    
    //監聽端口和處理請求
    server.requestHandler(new HttpServerHandler());
    
    // 啓動 Http服務 並處理請求
    server.listen(port,httpServerAsyncResult -> {
      if(httpServerAsyncResult.succeeded()){
        System.out.println("Server is now listening on port: "+port);
      }else{
        System.out.println("Failed to start server: "+httpServerAsyncResult.cause());
      }
    });
  }
}
