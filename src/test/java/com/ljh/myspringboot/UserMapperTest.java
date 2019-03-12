package com.ljh.myspringboot;

import com.ljh.myspringboot.entity.User;
import com.ljh.myspringboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/11/13 14:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() {
        User user = new User();
        user.setName("testname");
        user.setAge(45);
        userService.saveUser(user);
    }

}
