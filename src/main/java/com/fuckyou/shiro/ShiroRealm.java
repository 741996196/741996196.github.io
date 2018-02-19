package com.fuckyou.shiro;

import com.fuckyou.pojo.UserT;
import com.fuckyou.service.UserTService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陈源熹 on 2018-01-04.
 */
public class ShiroRealm extends AuthorizingRealm{

   /*
      1.doGetAuthenticationInfo  获取认证消息，如果数据库中没有数据，返回null,如果得到正确的用户名和密码，返回指定的对象

      2.AuthenticationInfo  可以使用SimpleAuthenticationInfo实现类，封装给你正确的用户名和密码

      3.token参数，就是我们需要认证的token
    */

      @Resource
     private UserTService userTService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SimpleAuthenticationInfo info = null;

        //1.将token转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //2.获取用户名即可

        //3.查询数据库，是否存在指定用户名和密码的用户
        UserT userT = new UserT();
        userT.setUserName(upToken.getUsername());

        UserT userT2 = userTService.selectUset(userT);
        if (userT2 != null) {
            Object principal = userT2.getUserName();

            Object creadentials = userT2.getPassword();

            String realmName = this.getName();
            //盐值加密
            ByteSource salt = ByteSource.Util.bytes(userT.getUserName());
            //
            SimpleHash sh = new SimpleHash("MD5", creadentials, salt, 1024);
            String md5 = sh.toString();
//              info=new SimpleAuthenticationInfo(principal,sh,realmName);
            info = new SimpleAuthenticationInfo(principal, sh, salt, realmName);
        } else {
            throw new AuthenticationException();
        }
        //4.如果查询到了，封装查询结果，返回给我们调用

        //5.如果没有查询到，抛出一个异常

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //返回值：AuthorizationInfo,封装获取的用户对应所有的角色SimpleAuthorizationInfo(Set<String>)
        //参数列表：PrincipalCollection 登录的身份，登录的用户名
        SimpleAuthorizationInfo info = null;
        UserT userT = new UserT();
        userT.setUserName(principalCollection.toString());

        UserT userT2 = userTService.selectUset(userT);
        if (userT2 != null) {
            Object principal = userT2.getUserName();

            Object creadentials = userT2.getPassword();

            String realmName = this.getName();
            //盐值加密
            ByteSource salt = ByteSource.Util.bytes(userT.getUserName());
            //
            SimpleHash sh = new SimpleHash("MD5", creadentials, salt, 1024);
            String md5 = sh.toString();
//              info=new SimpleAuthenticationInfo(principal,sh,realmName);
            Set<String> roles = new HashSet<String>();
            roles.add(userT2.getAge() + "");
            info = new SimpleAuthorizationInfo(roles);
        } else {
            throw new AuthenticationException();
        }
        return info;
    }
}
