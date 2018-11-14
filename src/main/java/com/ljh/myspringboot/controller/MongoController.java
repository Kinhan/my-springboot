package com.ljh.myspringboot.controller;

import com.ljh.myspringboot.exception.ExceptionUtil;
import com.ljh.myspringboot.model.User;
import com.ljh.myspringboot.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/11/1 13:55
 */
@RestController
@Slf4j
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private TestRepository repository;

    @GetMapping("/all")
    public ResponseEntity queryAll() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveUser(User user) {

        try {
            this.repository.save(user);
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping("/find/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id) {

        Optional<User> user = this.repository.findById(id);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/drop")
    public ResponseEntity dropUser(String id) {

        try {
            Optional<User> user = this.repository.findById(id);
            if (null != user && null != user.get()) {
                this.repository.deleteById(id);
                return new ResponseEntity(user, HttpStatus.OK);
            }else {
                return new ResponseEntity("用户不存在",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
