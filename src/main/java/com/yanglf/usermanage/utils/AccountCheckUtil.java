package com.yanglf.usermanage.utils;

import com.yanglf.usermanage.demain.BlogUser;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class AccountCheckUtil {


    public static void check(BlogUser user){

        Assert.notNull(user, "用户不能为空");
        String username = user.getUsername();
        Assert.notNull(username, "用户名不能为空");
        if (!ok(username,"[A-Za-z0-9]{6,30}")){
            throw new RuntimeException("用户名不合法");
        }
        String email = user.getEmail();
        Assert.notNull(email, "邮箱不能为空");
        if(!ok(email,"^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")){
            throw new RuntimeException("邮箱格式不合法");
        }

        String password = user.getPassword();
        Assert.notNull(password, "密码不能为空");
        if(!ok(password,"[A-Za-z0-9!@#^&-.]{6,30}")){
            throw new RuntimeException("密码格式不合法");
        }
    }

    private static boolean ok(String str,String reg){
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str).matches();
    }
}
