package com.wart.rpc.serializer;

import java.io.IOException;

/**
 * 序列化器 接口
 */
public interface Serializer {
  /**
   * 序列化
   * @param object
   * @param <T>
   * @return
   * @throws IOException
   */
  <T> byte[] serialize(T object) throws IOException;
  
  /**
   * 反序列化
   * @param bytes
   * @param clazz
   * @return
   * @param <T>
   * @throws IOException
   */
  <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException;
}
