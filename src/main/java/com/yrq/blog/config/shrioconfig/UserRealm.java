package com.yrq.blog.config.shrioconfig;

import com.yrq.blog.entity.User;
import com.yrq.blog.service.UserService;
import com.yrq.blog.utils.ShiroVerityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: blog_yrq
 * @description:  自定义Realm
 * @author: yrq
 * @create: 2020-03-28 09:21
 **/
public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;
    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new  SimpleAuthorizationInfo();
        info.addStringPermission("");

        //拿到user对象
        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User)subject.getPrincipal();
        //设置当前用户的权限
//        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if(StringUtils.isBlank(usernamePasswordToken.getUsername())){
            return null;
        }

        User user = userService.selectUser(usernamePasswordToken.getUsername());
        if(user == null){
            throw new AuthenticationException("用户信息认证失败");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(),
                user.getPassword(), getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return info;
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
//        String username = token.getUsername();
//        User user = userService.selectUser(username);
//        if(user == null){
//            throw new UnknownAccountException();
//        }else {
//            //盐
//            ByteSource salt = ByteSource.Util.bytes(user.getSalt());
//            try{
//                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
//                        user, user.getPassword(),   salt,  getName() );
//
//                return info;
//            }catch (Throwable t){
//                throw new AuthenticationException();
//            }
//        }
    }

}

