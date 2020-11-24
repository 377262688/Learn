package com.york.thread;

import com.york.thread.mq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@RequestMapping
@EnableCaching
@SpringBootApplication
public class ThreadDemoApplication implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        System.out.println("打印启动参数");
        for (String s : args) {
            System.out.println(args);
        }
        ConfigurableApplicationContext context = SpringApplication.run(ThreadDemoApplication.class, args);
//        OrderMapper orderMapper = (OrderMapper) context.getBean("orderMapper");
//        orderMapper.selectById("15120108195745801010019671");
//        OrderService orderService = (OrderService) context.getBean("orderService");
//        orderService.orderCheck();
//        orderService.sort();
    }
    @Autowired
    public Producer producer;
    @GetMapping("testSendMQMsg")
    public void testSendMQMsg() {
        producer.sendMsg();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    @Slf4j
//    @Service
//    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1",consumeThreadMax = 3)
//    public static class MyConsumer1 implements RocketMQListener<String> {
//        @Override
//        public void onMessage(String message) {
//            log.info("received message: {}", message);
//            System.out.println(System.currentTimeMillis());
//        }
//    }

}
