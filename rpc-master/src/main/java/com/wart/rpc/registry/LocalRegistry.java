package com.wart.rpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegistry {
  /**
   * 本地注册表
   */
  private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();
  
  /**
   * 注冊服務
   * @param serviceName
   * @param implementClass
   */
  public static void register(String serviceName,Class<?> implementClass){
    map.put(serviceName, implementClass);
  }
  
  public static Class<?> get(String serviceName){
    return map.get(serviceName);
  }
  
  public static void remove(String serviceName){
    map.remove(serviceName);
  }
}
