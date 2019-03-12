package com.ljh.myspringboot.controller;

import com.ljh.myspringboot.entity.User;
import com.ljh.myspringboot.exception.ExceptionUtil;
import com.ljh.myspringboot.repository.TestRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/11/1 13:55
 */
//@RestController
@Slf4j
@RequestMapping("/mongo")
@Api(tags = "2.1", description = "mongo测试", value = "mongo用户管理")
public class MongoController {

    @Autowired
    private TestRepository repository;

    @GetMapping("/all")
    @ApiOperation(value = "查询所有")
    public ResponseEntity<List<User>> queryAll() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加/修改用户（DONE）")
    public ResponseEntity saveUser(@RequestBody User user) {

        try {
            this.repository.save(user);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "主键查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "PATH"),
    })
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {

        Optional<User> user = this.repository.findById(id);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/drop")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = "String", paramType = "PATH")
    public ResponseEntity<User> dropUser(String id) {

        try {
            Optional<User> user = this.repository.findById(id);
            if (null != user && null != user.get()) {
                this.repository.deleteById(id);
                return new ResponseEntity(user, HttpStatus.OK);
            } else {
                return new ResponseEntity("用户不存在", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
