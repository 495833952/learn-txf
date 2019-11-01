package com.txf.controller;

import com.txf.entity.User;
import com.txf.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 2019/11/1.
 */
@RestController
public class HelloController {

    private static Log logger = LogFactory.getLog(HelloController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/adduser")
    public int addUser(@RequestParam("name")String name, @RequestParam("age")String age){
        return userService.addUser(name, age);
    }
    @RequestMapping("/findUser")
    public User findUser(@RequestParam("id") String id){
        return userService.findById(id);
    }
    @RequestMapping("/updataById")
    public String updataById(@RequestParam("id") String id,@RequestParam("name") String name){
        try {
            userService.updataById(id, name);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam("id") String id){
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
}
