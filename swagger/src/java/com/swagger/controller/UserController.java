package com.swagger.controller;

import com.swagger.entity.User;
import com.swagger.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongdongjian on 2017/8/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(
            value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加用户", httpMethod = "POST", notes = "add user")
    public Map<String, String> addUser(User user, HttpServletRequest request)
    {
        Map<String, String> map = new HashMap<String, String>();
        try {
            userService.save(user);
            map.put("result", "success");
        } catch (Exception e) {
            System.out.println(e);
            map.put("result", "failed");
        }
        return map;
    }
}
