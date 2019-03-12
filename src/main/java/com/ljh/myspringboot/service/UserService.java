package com.ljh.myspringboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljh.myspringboot.mapper.UserMapper;
import com.ljh.myspringboot.entity.User;
import com.ljh.myspringboot.service.base.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 10:15
 */
//@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;

    public PageInfo<User> findAll(Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        User condition = new User();
        condition.setStatus(1);
        List<User> users = this.userMapper.select(condition);
        PageInfo<User> pageInfo = new PageInfo<>(users);

        return pageInfo;

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @CachePut(value = "user", key = "\"user_\" + #user.getId()")
    public User saveUser(User user) {

        if (null != user && StringUtils.isBlank(user.getId())) {

            this.userMapper.insert(user);
            user.setStatus(1);

        } else if (null != user && StringUtils.isNotBlank(user.getId())) {
            this.userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }

    @Cacheable(value = "user", key = "\"user_\" + #id", unless = "#result == null")
    public User findById(String id) {

        User user = this.userMapper.selectByPrimaryKey(id);
        return user;

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @CacheEvict(value = "user", key = "\"user_\" + #id")
    public String dropUser(String id) {
        User user = new User();
        user.setId(id);
        user.setStatus(0);
        this.userMapper.updateByPrimaryKeySelective(user);
        return id;
    }
}
