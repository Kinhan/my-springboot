package com.ljh.myspringboot.repository;

import com.ljh.myspringboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/11/1 13:53
 */
public interface TestRepository extends MongoRepository<User, String>{

}
