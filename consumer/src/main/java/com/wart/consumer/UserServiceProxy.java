package com.wart.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wart.common.model.User;
import com.wart.common.service.UserService;
import com.wart.rpc.model.RpcRequest;
import com.wart.rpc.model.RpcResponse;
import com.wart.rpc.serializer.JdkSerializer;
import com.wart.rpc.serializer.Serializer;


import java.io.IOException;

/**
 * 靜態代理
 * 靈活性較差 一般不用
 */
public class UserServiceProxy implements UserService {
  @Override
  public User getUser(User user) {
    //指定序列化器
    Serializer serializer = new JdkSerializer();
    
    //建立RPC請求
    RpcRequest rpcRequest = RpcRequest.builder()
        .serviceName(UserService.class.getName())
        .methodName("getUser")
        .parameterTypes(new Class[]{User.class})
        .args(new Object[]{user})
        .build();
    
    try {
      byte[] bytes = serializer.serialize(rpcRequest);
      byte[] result;
      //請求服務
      try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
          .body(bytes)
          .execute()) {
        // 獲取結果 並 反序列化為byte[]
        result = httpResponse.bodyBytes();
      }
      //反序列化回應對象為RPCResponse類型
      RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
      return (User) rpcResponse.getData();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
