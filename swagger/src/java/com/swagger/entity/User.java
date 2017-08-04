package com.swagger.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by hongdongjian on 2017/8/2.
 */
@Entity
//@ApiModel("登陆请求参数")
public class User {
    private Integer id;

    @ApiModelProperty(value = "2",example = "name",required = true)
    private String username;
//    @ApiModelProperty(value = "1",required = true)
    private String password;
    private String sex;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
