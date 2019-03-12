package com.ljh.myspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.ljh.myspringboot.exception.ExceptionUtil;
import com.ljh.myspringboot.entity.User;
import com.ljh.myspringboot.service.UserService;
import com.ljh.myspringboot.util.restful.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 9:18
 */
//@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码,默认为1", dataType = "Integer", paramType = "QUERY"),
            @ApiImplicitParam(name = "pageSize", value = "分页大,默认为10", dataType = "Integer", paramType = "QUERY"),
    })
    public ModelAndView list(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("jsonp");

        view.addObject("title", "User列表");

        view.addObject("desc", "user列表");

        PageInfo<User> usersWithPageInfo = userService.findAll(pageNum, pageSize);
        view.addObject("users", usersWithPageInfo);

        return view;
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "主键查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "PATH"),
    })
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

    @PostMapping("/add")
    @ApiOperation(value = "添加用户（DONE）")
    public ResponseData addUser(@RequestBody User user) {
        try {
            if (user != null) {
                user.setId(null);
            }
            this.userService.saveUser(user);
            ResponseData res = ResponseData.ok();
            res.putDataValue("user", user);
            return res;
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return ResponseData.serverInternalError();

        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "修改用户（DONE）")
    public ResponseData updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            if (StringUtils.isNotBlank(id) && user != null) {
                user.setId(id);
            }
            this.userService.saveUser(user);
            ResponseData res = ResponseData.ok();
            res.putDataValue("user", user);
            return res;
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return ResponseData.serverInternalError();

        }
    }

    @DeleteMapping("/drop/{id}")
    @ApiOperation(value = "删除用户（DONE）")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = "String", paramType = "PATH")
    public ResponseData dropUser(@PathVariable String id) {
        try {
            this.userService.dropUser(id);
            return ResponseData.ok();
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return ResponseData.ok();

        }
    }


    public static void me(List<String> list){
        System.out.println("in-me-before="+list);

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            list.set(i,s+"in");
        }

        System.out.println("in-me-after="+list);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println("out-before="+list);
        me(list);
        System.out.println("out-after="+list);
    }


}
