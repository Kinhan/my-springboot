package com.ljh.myspringboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: linjinhan
 * @CreateDate: 2018/10/11 9:46
 */
@ApiModel
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @KeySql(sql = "select uuid_short()", order = ORDER.BEFORE)
    @ApiModelProperty(value = "id",hidden = true)
    private String id;

    @ApiModelProperty(value = "用户名",required = true)
    private String name;

    @ApiModelProperty(value = "年龄",required = true)
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号",required = true)
    private String cellphone;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "账号id",hidden = true)
    private String accountId;

    @ApiModelProperty(hidden = true)
    private Integer status;

}
