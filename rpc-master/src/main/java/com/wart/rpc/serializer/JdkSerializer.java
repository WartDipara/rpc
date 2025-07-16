package com.wart.rpc.serializer;

import java.io.*;

/**
 * JDK 序列化器
 */
public class JdkSerializer implements Serializer {
  
  /**
   * 序列化
   *
   * @param object
   * @param <T>
   * @return
   * @throws IOException
   */
  @Override
  public <T> byte[] serialize(T object) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    objectOutputStream.writeObject(object);
    objectOutputStream.close();
    return outputStream.toByteArray();
  }
  
  /**
   * 反序列化
   *
   * @param bytes 二进制数据
   * @param clazz 目标对象类
   * @param <T>   目标对象类
   * @return
   * @throws IOException
   */
  @Override
  public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
    try (
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
    ) {
      Object result = objectInputStream.readObject();
      if (!clazz.isInstance(result)) {
        throw new IOException("反序列化類型不匹配，期望類型為：" + clazz.getName() + "，實際類型：" + result.getClass().getName());
      }
      return clazz.cast(result);
    } catch (ClassNotFoundException e) {
      throw new IOException("找不到反序列化类：" + e.getMessage(), e);
    }
  }
  
}
