package com.ljh.myspringboot.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ljh.myspringboot.annotation.PassToken;
import com.ljh.myspringboot.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2019/1/7 15:56
 */
//@RestController
public class TokenController {

    @GetMapping(value = "/getToken")
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256("kinhan_lin-secret"));
        return token;
    }

}
