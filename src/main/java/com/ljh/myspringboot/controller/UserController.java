package com.ljh.myspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.ljh.myspringboot.exception.ExceptionUtil;
import com.ljh.myspringboot.model.User;
import com.ljh.myspringboot.service.UserService;
import com.ljh.myspringboot.util.restful.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 9:18
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("user-list");

        view.addObject("title", "User列表");

        view.addObject("desc", "user列表");

        PageInfo<User> usersWithPageInfo = userService.findAll(pageNum, pageSize);
        view.addObject("users", usersWithPageInfo);

        return view;
    }

    @RequestMapping("/find/{id}")
    public ModelAndView getUser(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("user-detail");

        view.addObject("title", "User详情");

        view.addObject("desc", "user详情");

        User user = this.userService.findById(id);
        view.addObject("user", user);

        return view;
    }

    @PostMapping("/save")
    public ResponseData saveUser(User user) {

        try {
            this.userService.saveUser(user);
            ResponseData res = ResponseData.ok();
            res.putDataValue("user",user);
            return res;
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return ResponseData.serverInternalError();

        }
    }

    @PostMapping("/drop")
    public ResponseData dropUser(String id) {

        try {
            this.userService.dropUser(id);
            return ResponseData.ok();
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return ResponseData.ok();

        }
    }

}
