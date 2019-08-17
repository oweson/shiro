package cn.itcast.shiro.utils;

import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/31 0031 7:56
 */
public class Dog {
    @RequiresRoles("admin")
    public void demo1(){}
    public static void main(String[] args) {

    }
}
