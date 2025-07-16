package com.wart.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 設計模式
 * 代理工廠
 */
public class ServiceProxyFactory {
  /**
   * 獲取代理對象
   * @param serviceClass
   * @return
   * @param <T>
   */
  @SuppressWarnings("unchecked")
  public static <T> T getProxy(Class<T> serviceClass){
    return (T) Proxy.newProxyInstance(
        serviceClass.getClassLoader(),
        new Class[]{serviceClass},
        new ServiceProxy());
  }
}
