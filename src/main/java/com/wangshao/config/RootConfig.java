package com.wangshao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

/**
 * @author liutao
 * @create 2020-02-23-23:30
 */
//spring容器不扫描controller
@ComponentScan(value = "com.wangshao",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class RootConfig {
}
