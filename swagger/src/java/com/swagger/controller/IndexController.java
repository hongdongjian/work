package com.swagger.controller;

import com.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hongdongjian on 2017/8/2.
 */
@Controller
@RequestMapping("/")
@Api(value = "/", description = "主页相关api")
public class IndexController {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户登陆界面",httpMethod = "GET")
    public String loginPage(){
        System.out.println("login.html");
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html",method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆账号检查",httpMethod = "POST")
    @ResponseBody
    public String loginCheck(HttpServletRequest request, @RequestBody User loginCommand){
       return loginCommand.getUsername();
    }
}
