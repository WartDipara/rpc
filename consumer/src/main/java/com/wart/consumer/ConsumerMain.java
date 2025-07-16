package com.wart.consumer;

import com.wart.common.model.User;
import com.wart.common.service.UserService;
import com.wart.rpc.proxy.ServiceProxyFactory;

public class ConsumerMain {
  public static void main(String[] args) {
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
