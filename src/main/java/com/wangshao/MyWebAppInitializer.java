package com.wangshao;

import com.wangshao.config.RootConfig;
import com.wangshao.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author liutao
 * @create 2020-02-23-23:22
 */

//web容器器启动的时候创建对象,调用方法来初始化容器以前前段控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取根容器配置类(spring的配置文件)  父容器
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类(springmvc配置文件)  子容器
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 获取dispatcherService的映射信息
     * @return
     *    /:拦截所有请求(包括静态资源(xx.js,xx.png)),但不包括*.jsp
     *    /*:拦截所有请求,连*.jsp页面都拦截:jsp页面是tomcat的jsp引擎解析的
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
