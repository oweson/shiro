package com.example.springbootshiro.dao;

import com.example.springbootshiro.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    /**
     * 1 通过username查找用户信息;
     */
    UserInfo findByUsername(String username);
}