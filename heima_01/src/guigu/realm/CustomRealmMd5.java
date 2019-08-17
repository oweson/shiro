package guigu.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


public class CustomRealmMd5 extends AuthorizingRealm {

    // 0 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealmMd5");
    }

    // 1 用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {

        // 1 token是用户输入的,第一步从token中取出身份信息
        String userCode = (String) token.getPrincipal();

        // 2 根据用户输入的userCode从数据库查询
        // ....

        // 如果查询不到返回null,数据库中用户账号是zhangsansan
        /*
         * if(!userCode.equals("zhangsansan")){// return null; }
         */

        // 模拟从数据库查询到密码,散列值
        String password = "12a79230e8830d1d6abb52cfca5036d6";
        // 从数据库获取salt
        String salt = "ppx";
        //上边散列值和盐对应的明文：123456

        // 如果查询到返回认证信息AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userCode, password, ByteSource.Util.bytes(salt), this.getName());

        return simpleAuthenticationInfo;
    }

    // 2 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }

}
