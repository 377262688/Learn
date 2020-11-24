package com.york.thread.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author york
 * @create 2020-04-10 10:50
 **/
public class LoggerHandler implements InvocationHandler {

    private Object target;

    public LoggerHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object reslut = method.invoke(target,args);
        after();
        return reslut;
    }

    private void before() {
        System.out.println("打印前置日志" + System.currentTimeMillis());
    }

    private void after() {
        System.out.println("打印后置日志" + System.currentTimeMillis());
    }
}
