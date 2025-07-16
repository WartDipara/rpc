package com.wart.provider;

import com.wart.common.model.User;
import com.wart.common.service.UserService;

public class UserServiceImplement implements UserService {
  
  @Override
  public User getUser(User user) {
    System.out.println("User Name: "+ user.getName());
    return user;
  }
}
