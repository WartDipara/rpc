package com.wart.provider;

import com.wart.common.service.UserService;
import com.wart.rpc.registry.LocalRegistry;
import com.wart.rpc.server.HttpServer;
import com.wart.rpc.server.VertxHttpServer;

public class ProviderMain {
  public static void main(String[] args) {
    // 注册服务
    LocalRegistry.register(UserService.class.getName(),UserServiceImplement.class);
    //啓動web服務
    HttpServer httpServer = new VertxHttpServer();
    httpServer.doStart(8080);
  }
}
