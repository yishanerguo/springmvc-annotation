package com.wangshao.service;

import org.springframework.stereotype.Service;

/**
 * @author liutao
 * @create 2020-02-23-23:38
 */

@Service
public class HelloService {

    public String sayHello(String name){
        return "hello"+name;
    }
}
