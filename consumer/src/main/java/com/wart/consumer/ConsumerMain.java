package com.wart.consumer;

import com.wart.common.model.User;
import com.wart.common.service.UserService;

public class ConsumerMain {
  public static void main(String[] args) {
    UserService userService = null;
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
