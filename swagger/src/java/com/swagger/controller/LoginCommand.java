package com.swagger.controller;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by hongdongjian on 2017/8/2.
 */


@ApiModel("登陆请求参数")
public class LoginCommand {

    @ApiModelProperty(value = "登陆账号",required = false)
    private String userName;
    @ApiModelProperty(value = "登陆密码",required = false)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
