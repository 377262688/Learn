package com.york.thread.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.york.thread.entity.Order;
import com.york.thread.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by york on 2019/3/28.
 */
@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public void orderCheck() {
        int count = orderMapper.selectCount(new QueryWrapper<Order>());
        log.info("总人数为：" + count);
    }

    public void sort() {
        log.info("开始查询订单");
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().last("limit 0,10000"));
        Order temp;
        log.info("查询完成");
        // 插入排序：时间复杂度 o（n@）
        long start1 = System.currentTimeMillis();
        for (int i = 0;i<orders.size();i++) {
            for (int j = 1; j < orders.size(); j++) {
                if (orders.get(j).compareTo(orders.get(j-1)) > 0) {
                    temp = orders.get(j);
                    orders.set(j,orders.get(j-1));
                    orders.set(j-1,temp);
                }
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("插入排序耗时：" + (end1 - start1) + "ms");
        // 选择排序
        int minIndex = 0;
        for (int i=0;i<orders.size()-1;i++) {
            for (int j = minIndex;j<orders.size();j++) {
                if (orders.get(j).compareTo(orders.get(minIndex)) > 0) {

                }
            }
        }
    }

    @Cacheable(cacheNames = "order",key = "#id")
    public Order getOrder(String id) {
        log.info("查询订单{}",id);
        return orderMapper.selectById(id);
    }

    @CachePut(cacheNames = "order",key = "#id")
    public Order updateOrder(String id,String orderSup) {
        log.info("更新订单{}",id);
        Order order = orderMapper.selectById(id);
        order.setOrderSupplier(orderSup);
        orderMapper.updateById(order);
        return order;
    }

    @CacheEvict(cacheNames = "order",key = "#id")
    public void deleteOrder(String id) {
        log.info("删除订单{}",id);
    }
}
