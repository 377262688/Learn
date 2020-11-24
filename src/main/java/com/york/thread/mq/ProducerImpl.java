package com.york.thread.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author york
 * @create 2020-03-10 20:52
 **/
@Service
public class ProducerImpl implements Producer, ApplicationContextAware {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private ApplicationContext applicationContext;
    @Override
    public void sendMsg() {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
