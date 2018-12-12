package cn.itcast.shiro.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;


public class MD5Test {
    @Test
    public void demo1() {
        String source = "123456";
        String salt = "ppx";
        Integer hashIter = 2;
        Md5Hash md5Hash = new Md5Hash(source, salt, hashIter);
        System.out.println(md5Hash.toString());
        /**第一个的参数知道加密的算法*/
        //构造方法中：
        //第一个参数：明文，原始密码
        //第二个参数：盐，通过使用随机数
        //第三个参数：散列的次数，比如散列两次，相当 于md5(md5(''))等同与两次md5明文加密
        //第一个参数：加密算法

        SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIter);
        System.out.println(simpleHash);
    }

    public static void main(String[] args) {


    }

}
