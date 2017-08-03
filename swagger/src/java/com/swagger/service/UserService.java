package com.swagger.service;

import com.swagger.dao.UserDao;
import com.swagger.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hongdongjian on 2017/8/2.
 */
@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserDao userDao;

    @Resource
     public void setUserDao(UserDao userDao) {
         super.setBaseDao(userDao);
     }
}
