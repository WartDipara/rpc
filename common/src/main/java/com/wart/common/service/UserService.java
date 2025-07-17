package com.wart.common.service;

import com.wart.common.model.User;

/**
 * UserService
 */
public interface UserService {
  /**
   * getUser
   * @return  User
   */
  User getUser(User user);
  
  /**
   * 測試 mock
   * @return
   */
  default short getNumber(){
    return 1;
  }
  
}
