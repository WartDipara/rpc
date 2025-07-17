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
    
    UserService userService;
    if(rpcConfig.isMock()){
      userService = ServiceProxyFactory.getMockProxy(UserService.class);
      System.out.println("mock 測試環境");
      long number = userService.getNumber();
      System.out.println(number);
    }
    userService=ServiceProxyFactory.getProxy(UserService.class);
    User user =new User();
    user.setName("test");
    
    User newUser=userService.getUser(user);
    if(newUser!=null){
      System.out.println("User Name: "+ newUser.getName());
    }else{
      System.out.println("User is null");
    }
}
}
