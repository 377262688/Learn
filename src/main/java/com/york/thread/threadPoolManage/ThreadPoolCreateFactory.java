package com.york.thread.threadPoolManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangjianzhong
 * @description
 * @date 2021/8/16 3:48 下午
 **/
@Component
public class ThreadPoolCreateFactory {

    private static Map<String, ThreadPoolExecutor> map = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;

    public ThreadPoolExecutor getThreadPool(String name) {
        String applicationName = applicationContext.getApplicationName();
        return null;
    }
}
