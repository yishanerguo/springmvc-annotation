package com.wangshao.controller;

import com.wangshao.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author liutao
 * @create 2020-02-24-13:14
 */

@Controller
public class AsyncContorller {

    /**
     * 1.控制器返回callable
     * 2.spring异步处理,将callable提交到taskExecutor使用一个隔离的线程进行执行
     * 3.DispatcherServlet和所有的Filter退出web容器的线程,但是response保持打开状态
     * 4.callable返回结果,springmvc将请求重新派发给容器,回复之前的处理
     * 5.根据callable返回的结果,springmvc继续进行视图渲染流程等(冲收到请求--->视图渲染)
     * @return
     * preHandle............/async
     * 主线程开始......Thread[http-bio-8080-exec-8,5,main]==>1582522419993
     * 主线程结束......Thread[http-bio-8080-exec-8,5,main]==>1582522419994
     * =========================dispatcherServlet及所有的Filter退出线程
     *
     * ========================等待callable执行===============
     * 副线程开始......Thread[MvcAsync1,5,main]==>1582522420000
     * 副线程结束......Thread[MvcAsync1,5,main]==>1582522422000
     * ======================callable执行====================
     *
     * preHandle............/async
     * postHandle............
     *
     * 可以看到:异步状态下不能拦截到异步处理的业务逻辑
     *   异步的拦截器:
     *    1.原生api的AsyncListener
     *    2.springmvc:实现AsyncHandlerInterceptor
     *
     */
    @ResponseBody
    @RequestMapping("/async")
    public Callable<String> async01(){
        System.out.println("主线程开始......"+Thread.currentThread()+"==>"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {

            public String call() throws Exception {
                System.out.println("副线程开始......"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("副线程结束......"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                return "callable<String> async01()";
            }
        };
        System.out.println("主线程结束......"+Thread.currentThread()+"==>"+System.currentTimeMillis());

        return callable;
    }

    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object>  createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<Object>((long) 3000,"create fail.....");
        DeferredResultQueue.save(deferredResult);
        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create(){
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success"+order;
    }
}
