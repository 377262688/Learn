package com.york.thread.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author york
 * @create 2020-04-10 10:53
 **/
public class Client {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new LoggerHandler(userService);
        UserService proxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),userService.getClass().getInterfaces(),invocationHandler);
        proxy.select();
        proxy.update();
    }
}
