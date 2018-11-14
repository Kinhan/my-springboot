package com.ljh.myspringboot.controller;

import com.google.gson.JsonObject;
import com.ljh.myspringboot.util.log.LogHelper;
import com.ljh.myspringboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/30 11:54
 */
@RestController
@Slf4j
@RequestMapping("/ua")
public class UaTestController {

    @GetMapping("info")
    public ResponseEntity getUaInfo(HttpServletRequest request, HttpServletResponse response){

        String agent= request.getHeader("user-agent");


        log.error("================》开始写入特定日志");
        JsonObject uaMsg = new JsonObject();
        uaMsg.addProperty("ua",agent);
        LogHelper.log2File("My-Info2",uaMsg);
        User user = new User();
        user.setId("qerwefad");
        user.setName("safdsf");
        user.setAge(22);
        user.setAccountId("ewrytrdxg");
        LogHelper.log2File("user",User.class,user);
        log.error("================》写入特定日志完成");


        return new ResponseEntity(agent,HttpStatus.OK);
    }


}
