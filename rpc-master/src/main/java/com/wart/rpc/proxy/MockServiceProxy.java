package com.wart.rpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock proxy
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
  
  /**
   *
   * @return
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Class<?> methodReturnType = method.getReturnType();
    log.info("mock invoke {}", method.getName());
    return getDefaultObject(methodReturnType);
  }
  
  /**
   * default  value
   *
   * @param type
   * @return
   */
  private Object getDefaultObject(Class<?> type) {
    // 基本類型
    if (type.isPrimitive()) {
      if (type == boolean.class) {
        return false;
      } else if (type == short.class) {
        return (short) 0;
      } else if (type == int.class) {
        return 0;
      } else if (type == long.class) {
        return 0L;
      }
    }
    // 對象類型
    return null;
  }
}
