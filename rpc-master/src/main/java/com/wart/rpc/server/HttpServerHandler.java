package com.wart.rpc.server;

import com.wart.rpc.model.RpcRequest;
import com.wart.rpc.model.RpcResponse;
import com.wart.rpc.registry.LocalRegistry;
import com.wart.rpc.serializer.JdkSerializer;
import com.wart.rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * HTTP request handler
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
  /**
   * HTTP request handler
   *
   * @param httpServerRequest
   */
  @Override
  public void handle(HttpServerRequest httpServerRequest) {
    // 指定序列器
    final Serializer serializer = new JdkSerializer();
    
    System.out.println("Received request: " + httpServerRequest.method() + " " + httpServerRequest.uri());
    
    //異步處理
    httpServerRequest.bodyHandler(
        buffer -> {
          byte[] bytes = buffer.getBytes();
          RpcRequest rpcRequest = null;
          try {
            rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
          } catch (Exception e) {
            e.printStackTrace();
          }
          
          RpcResponse rpcResponse = new RpcResponse();
          if (rpcRequest == null) {
            rpcResponse.setMessage("rpcRequest is null");
            doResponse(httpServerRequest, rpcResponse, serializer);
            return;
          }
          
          try {
            //獲取實現类 反射機制
            Class<?> implementClass = LocalRegistry.get(rpcRequest.getServiceName());
            Method method = implementClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
            Object result = method.invoke(implementClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
            //封裝回應
            rpcResponse.setData(result);
            rpcResponse.setDataType(result.getClass());
            rpcResponse.setMessage("success");
          } catch (Exception e) {
            e.printStackTrace();
            rpcResponse.setException(e);
            rpcResponse.setMessage(e.getMessage());
          }
          doResponse(httpServerRequest, rpcResponse, serializer);
        }
    );
  }
  
  /**
   * 响应
   *
   * @param httpServerRequest
   * @param rpcResponse
   * @param serializer
   */
  private void doResponse(HttpServerRequest httpServerRequest, RpcResponse rpcResponse, Serializer serializer) {
    HttpServerResponse httpServerResponse = httpServerRequest.response().putHeader("Content-Type", "application/json");
    try {
      //序列化
      byte[] bytes = serializer.serialize(rpcResponse);
      httpServerResponse.end(Buffer.buffer(bytes));
    } catch (IOException e) {
      e.printStackTrace();
      httpServerResponse.end(Buffer.buffer());
    }
  }
}
