package com.wangshao.controller;

import com.wangshao.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liutao
 * @create 2020-02-23-23:36
 */

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        String hello = helloService.sayHello("tomcat.....");
        return hello;
    }

    @RequestMapping("/suc")
    public String success(){
        return "success";
    }
}
