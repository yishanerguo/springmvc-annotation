package com.wangshao.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author liutao
 * @create 2020-02-24-13:53
 */


public class DeferredResultQueue {

    private  static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<DeferredResult<Object>>();

    public static  void save(DeferredResult<Object> deferredResult) {
        queue.add(deferredResult);
    }

    public  static DeferredResult<Object> get(){
        return queue.poll();
    }
}
