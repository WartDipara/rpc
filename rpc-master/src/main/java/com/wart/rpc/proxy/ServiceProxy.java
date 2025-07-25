package com.wart.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wart.rpc.model.RpcRequest;
import com.wart.rpc.model.RpcResponse;
import com.wart.rpc.serializer.JdkSerializer;
import com.wart.rpc.serializer.Serializer;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服務代理类 動態代理
 */
public class ServiceProxy implements InvocationHandler {
  /**
   * 調用代理
   *
   * @param proxy  the proxy instance that the method was invoked on
   * @param method the {@code Method} instance corresponding to
   *               the interface method invoked on the proxy instance.  The declaring
   *               class of the {@code Method} object will be the interface that
   *               the method was declared in, which may be a superinterface of the
   *               proxy interface that the proxy class inherits the method through.
   * @param args   an array of objects containing the values of the
   *               arguments passed in the method invocation on the proxy instance,
   *               or {@code null} if interface method takes no arguments.
   *               Arguments of primitive types are wrapped in instances of the
   *               appropriate primitive wrapper class, such as
   *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
   * @return
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Serializer serializer = new JdkSerializer();
    // 組裝請求參數
    RpcRequest rpcRequest = RpcRequest.builder()
        .serviceName(method.getDeclaringClass().getName())
        .methodName(method.getName())
        .parameterTypes(method.getParameterTypes())
        .args(args)
        .build();
    try{
      //序列化
      byte[] bytes = serializer.serialize(rpcRequest);
      //send
      //TODO post待優化
      try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bytes).execute()){
        byte[] result = httpResponse.bodyBytes();
        //反序列化
        RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
        return rpcResponse.getData();
      }
    }catch (IOException e){
      e.printStackTrace();
    }
    return null;
  }
}
