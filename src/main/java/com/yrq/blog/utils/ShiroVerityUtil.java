package com.yrq.blog.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: blog_yrq
 * @description:
 * @author: yrq
 * @create: 2020-03-28 12:40
 **/
public class ShiroVerityUtil {

    /**
     * shiro 盐值加密
     */
    public static Map encryptPassword(String pwd) {
        Map<String,String> map  = new HashMap<>();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String password = new Md5Hash(pwd,salt,2).toString();
        map.put("salt",salt);
        map.put("password", password);
        num = salt;
        return map;

    }

    public static String num ;

}
class  Test{
    public static void main(String[] args) {
        Map<String,String> map = ShiroVerityUtil.encryptPassword("123456");
        System.out.println(map.get("salt"));
        System.out.println(map.get("password"));

        System.out.println("*******************************************");
        String salt  = map.get("salt");
        String password = new SimpleHash("MD5","123456",salt,2).toString();
        System.out.println(salt);
        System.out.println(password);

    }
}