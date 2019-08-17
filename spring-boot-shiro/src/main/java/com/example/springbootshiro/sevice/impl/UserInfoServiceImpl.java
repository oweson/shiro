package com.example.springbootshiro.sevice.impl;

import com.example.springbootshiro.dao.UserInfoDao;
import com.example.springbootshiro.model.UserInfo;
import com.example.springbootshiro.sevice.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    /**
     * 1 根据用户名查找用户的信息
     */
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}