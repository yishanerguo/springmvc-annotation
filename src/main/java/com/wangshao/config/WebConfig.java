package com.wangshao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author liutao
 * @create 2020-02-23-23:30
 */
//springmvc只扫描controller:子容器
//useDefaultFilters=false  禁用默认的过滤规则
@ComponentScan(value = "com.wangshao",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
public class WebConfig {
}
