package com.wart.consumer;

import com.wart.common.model.User;
import com.wart.common.service.UserService;
import com.wart.rpc.config.RpcConfig;
import com.wart.rpc.proxy.ServiceProxyFactory;
import com.wart.rpc.utils.ConfigUtils;

public class ConsumerMain {
  public static void main(String[] args) {
    //debug
    RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
    System.out.println(rpcConfig);
    
    // 靜態代理
//    UserService userService = new UserServiceProxy();
    //動態代理方法
    UserService userService = ServiceProxyFactory.getProxy(UserService.class);
    User user = new User();
    user.setName("wart");
    
    
    User newUser = userService.getUser(user);
    if(newUser!=null){
      System.out.println(newUser.getName());
    }else{
      System.out.println("User is null");
    }
  }
}
