package com.swagger.controller;

import com.swagger.entity.User;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by hongdongjian on 2017/8/2.
 */
@Controller
@RequestMapping("/")
@Api(value = "ni",description = "主页信息")
public class IndexController {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户登陆界面",httpMethod = "GET")
    public String loginPage(){
        System.out.println("login.html");
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html",method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆账号检查",notes = "检查2",response = User.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 404, message = "服务器内部异常")})
    @ResponseBody
    public User loginCheck(@ApiParam(value = "用户信息", required = true) @RequestBody User user, String note){
       return user;
    }
}
