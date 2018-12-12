package cn.itcast.shiro.realm;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2018/10/19 0019 17:28
 */

/**
 * 1 授权方法
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    public void setName(String name) {
        super.setName("ppx");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 2 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /** 1 token是用户输入的，第一从token中取出身份信息*/
        String principal = (String) authenticationToken.getPrincipal();
        /** 2 第二步：根据用户输入的userCode从数据库查询,
         * 查询不到返回null,
         * 假如用户名字ppx,*/
        /*if(!userCode.equals("ppx")){//
			return null;
		}*/
        if (!principal.equalsIgnoreCase("ppx")) {
            return null;
        }
        /** 3 模拟从数据库查询到密码*/
        String password = "123456";
        /** 4 如果查询到，返回认证信息*/
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, password, this.getName());
        return simpleAuthenticationInfo;
    }
}
