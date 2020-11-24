package com.york.thread.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by york on 2019/3/27.
 */
@Data
@TableName("tyfo_order")
public class Order implements Comparable<Order>, Serializable {
    private static final long serialVersionUID = -2428720210855948124L;
    @TableId
    private String orderSn;
    private String orderSupplier;
    private long orderTime;
    private String orderNo;
    private String orderJsdmph;
    private long orderCptime;
    private String orderCw;
    private String orderCk;
    private String orderSfz;
    private BigDecimal orderRyf;
    private BigDecimal orderJjf;
    private long orderZftime;
    private String orderXc;
    private BigDecimal orderPrice;
    private long orderCtime;
    private String shopId;
    private String orderCompany;

    @Override
    public int compareTo(Order o) {
        if (o.orderTime > this.orderTime) {
            return 1;
        }
        return 0;
    }
}
