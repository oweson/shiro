package cn.itcast.shiro.authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class AuthorizationTest {
    @Test
    public void MyTest() {
        /** 2  创建SecurityManager工厂*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-permission.ini");

        /** 3 创建SecurityManager*/
        SecurityManager securityManager = factory.getInstance();

        /** 4  将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理*/
        SecurityUtils.setSecurityManager(securityManager);

        /** 5 创建subject*/
        Subject subject = SecurityUtils.getSubject();

        /** 6 创建token令牌*/
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
                "123");

        /** 7 执行认证*/
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认真通过" + authenticated);
        boolean role1 = subject.hasRole("role1");
        System.out.println("有角色1的权限" + role1);
        boolean b = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("有着一组权限吗" + b);
        /**.shiro.authz.UnauthorizedException: Subject does not have role [role3]
         *         subject.checkRoles("role3");检查角色是否存在
         *         不存在就报异常
         * */
        /**核心：
         * user:create
         * 后面默认是user:create * 表示所有*/
        boolean permitted = subject.isPermitted("user:create:1");
        System.out.println("有权限吗" + permitted);
        boolean permitted1 = subject.isPermitted("user:delete:211000");
        System.out.println("有删除的权限吗" + permitted1);


    }

    /**
     * 1 角色授权、资源授权测试
     */
    @Test
    public void testAuthorization() {

        /** 2  创建SecurityManager工厂*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-permission.ini");

        /** 3 创建SecurityManager*/
        SecurityManager securityManager = factory.getInstance();

        /** 4  将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理*/
        SecurityUtils.setSecurityManager(securityManager);

        /** 5 创建subject*/
        Subject subject = SecurityUtils.getSubject();

        /** 6 创建token令牌*/
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
                "123");

        /** 7 执行认证*/
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("认证状态：" + subject.isAuthenticated());
        /** 1 认证通过后执行授权*/

        /** 2  基于角色的授权
         hasRole传入角色标识*/
        boolean ishasRole = subject.hasRole("role1");
        System.out.println("单个角色判断" + ishasRole);
        /** 3  hasAllRoles是否拥有多个角色*/
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1",
                "role2", "role3"));
        System.out.println("多个角色判断" + hasAllRoles);

        /** 4 使用check方法进行授权，如果授权不通过会抛出异常*/
        subject.checkRole("role13");
        System.out.println("-----------------------------------------------------------");

        /** 1  基于资源的授权,
         * isPermitted传入权限标识符*/
        boolean isPermitted = subject.isPermitted("user:create:1");
        System.out.println("单个权限判断" + isPermitted);

        boolean isPermittedAll = subject.isPermittedAll("user:create:1",
                "user:delete");
        System.out.println("多个权限判断" + isPermittedAll);

        /** 2  使用check方法进行授权，如果授权不通过会抛出异常*/
        subject.checkPermission("items:create:1");

    }

    /**
     * 3 自定义realm进行资源授权测试
     */
    @Test
    public void testAuthorizationCustomRealm() {

        // 创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro-realm.ini");

        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();

        // 将SecurityManager设置到系统运行环境，和spring后将SecurityManager配置spring容器中，一般单例管理
        SecurityUtils.setSecurityManager(securityManager);

        // 创建subject
        Subject subject = SecurityUtils.getSubject();

        // 创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan",
                "111111");

        // 执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("认证状态：" + subject.isAuthenticated());
        // 认证通过后执行授权

        // 基于资源的授权，调用isPermitted方法会调用CustomRealm从数据库查询正确权限数据
        // isPermitted传入权限标识符，判断user:create:1是否在CustomRealm查询到权限数据之内
        boolean isPermitted = subject.isPermitted("user:create:1");
        System.out.println("单个权限判断" + isPermitted);

        boolean isPermittedAll = subject.isPermittedAll("user:create:1",
                "user:create");
        System.out.println("多个权限判断" + isPermittedAll);

        // 使用check方法进行授权，如果授权不通过会抛出异常
        subject.checkPermission("items:add:1");

    }

}
