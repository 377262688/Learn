package com.york.thread;

import com.york.thread.entity.Order;
import com.york.thread.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author york
 * @create 2019-10-19 21:35
 **/
@RestController
public class CacheController {
    @Autowired
    private OrderService orderService;
    @GetMapping("getOrder")
    public Order getOrder(String id, HttpServletResponse response) {
        System.out.println(response.getHeader("Connection"));
        return orderService.getOrder(id);
    }

    @GetMapping("updateOrder")
    public Order updateOrder(String id,String orderSup) {
        Order order = orderService.updateOrder(id, orderSup);
        return order;
    }

    @GetMapping("deleteOrder")
    public void deleteOrder(String id) {
        orderService.deleteOrder(id);
    }
}
