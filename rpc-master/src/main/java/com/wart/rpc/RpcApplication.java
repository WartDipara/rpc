package com.wart.rpc;

import com.wart.rpc.config.RpcConfig;
import com.wart.rpc.constant.RpcConstant;
import com.wart.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcApplication {
  private static volatile RpcConfig rpcConfig;
  
  /**
   * init 自定義配置
   * @param config
   */
  public static void init(RpcConfig config){
    rpcConfig=config;
    log.info("RpcApplication init: config= {}",config.toString());
  }
  
  /**
   * init
   */
  public static void init(){
    RpcConfig config;
    try{
      config= ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
    }catch (Exception e){
      config=new RpcConfig();
      log.warn("RpcApplication init: load config error, use default config");
    }
    init(config);
  }
  
  /**
   * 獲取配置
   * @return
   */
  public static RpcConfig getRpcConfig(){
    // 雙檢鎖單例模式
    if(rpcConfig==null){
      synchronized (RpcApplication.class){
        if(rpcConfig==null)
          init();
      }
    }
    return rpcConfig;
  }
}
