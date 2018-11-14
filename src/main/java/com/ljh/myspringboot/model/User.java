package com.ljh.myspringboot.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 9:46
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @KeySql(sql = "select uuid_short()", order = ORDER.BEFORE)
    private String id;

    private String name;

    private Integer age;

    private String email;

    private String cellphone;

    private String accountId;

    private Integer status;

}
