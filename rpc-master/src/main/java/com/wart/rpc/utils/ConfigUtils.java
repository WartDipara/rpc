package com.wart.rpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具
 */
public class ConfigUtils {
  /**
   * 加载配置
   * @param clazz
   * @param prefix
   * @return
   * @param <T>
   */
  public static <T> T loadConfig(Class<T> clazz,String prefix){
    return loadConfig(clazz, prefix, "");
  }
  
  /**
   * 分區加載配置
   * @param clazz
   * @param prefix
   * @param environment
   * @return
   * @param <T>
   */
  public static <T> T loadConfig(Class<T> clazz,String prefix,String environment){
    StringBuilder configFileBuilder = new StringBuilder("application");
    if(StrUtil.isNotBlank(environment)){
      configFileBuilder.append("-").append(environment);
    }
    configFileBuilder.append(".properties");
    Props props = new Props(configFileBuilder.toString());
    return props.toBean(clazz, prefix);
  }
}
