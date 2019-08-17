package com.example.springbootshiro.sevice;


import com.example.springbootshiro.model.UserInfo;

public interface UserInfoService {
    /**
     * 1 通过username查找用户信息;
     */
     UserInfo findByUsername(String username);
}