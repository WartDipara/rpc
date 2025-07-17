package com.wart.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 設計模式
 * 代理工廠
 */
public class ServiceProxyFactory {
  /**
   * 獲取代理對象
   *
   * @param serviceClass
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T> T getProxy(Class<T> serviceClass) {
    return (T) Proxy.newProxyInstance(
        serviceClass.getClassLoader(),
        new Class[]{serviceClass},
        new ServiceProxy());
  }
  
  /**
   * 根據服務類獲取Mock對象
   *
   * @param serviceClass
   * @param <T>
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T> T getMockProxy(Class<T> serviceClass) {
    return (T) Proxy.newProxyInstance(
        serviceClass.getClassLoader(),
        new Class[]{serviceClass},
        new MockServiceProxy());
  }
}
