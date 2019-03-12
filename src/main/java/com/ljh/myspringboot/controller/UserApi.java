package com.ljh.myspringboot.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ljh.myspringboot.annotation.UserLoginToken;
import com.ljh.myspringboot.entity.User;
import com.ljh.myspringboot.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2019/1/7 16:16
 */
//@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
//    @Autowired
//    TokenService tokenService;
    //登录
    @PostMapping("/login")
    public Object login(@RequestBody User user) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findById(user.getId());
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
                String token =  JWT.create().withAudience(user.getId())
                        .sign(Algorithm.HMAC256("kinhan_lin-secret"));
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
