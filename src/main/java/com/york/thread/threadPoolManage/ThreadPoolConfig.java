package com.york.thread.threadPoolManage;

import lombok.Data;

/**
 * @author yangjianzhong
 * @description 线程池配置
 * @date 2021/8/16 3:54 下午
 **/
@Data
public class ThreadPoolConfig {

    private String appName;

    private String threadPoolName;

    private Integer corePoolSize;

    private Integer maximumPoolSize;

    private Integer queueType;

    private Integer queueLength;

    private boolean warn;

    private Integer capacityWarnThreshold;

    // 活跃度告警阈值
    private Integer activeWarnThreshold;
}
