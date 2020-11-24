package com.york.thread.reflect.proxy;

/**
 * @author york
 * @create 2020-04-10 10:48
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void select() {
        System.out.println("查询selectById");
    }

    @Override
    public void update() {
        System.out.println("update");
    }
}
