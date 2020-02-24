package com.wangshao.config;

import com.wangshao.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import org.springframework.stereotype.Controller;

import org.springframework.web.servlet.config.annotation.*;



/**
 * @author liutao
 * @create 2020-02-23-23:30
 */
//springmvc只扫描controller:子容器
//useDefaultFilters=false  禁用默认的过滤规则
@ComponentScan(value = "com.wangshao",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    //定制


    /**
     * 视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从/WEB-INF/   XXX.jsp
        //registry.jsp();
        registry.jsp("", ".jsp");
    }

    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    //拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }
}
