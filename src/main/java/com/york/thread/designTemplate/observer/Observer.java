package com.york.thread.designTemplate.observer;

/**
 * @author york
 * @create 2020-03-26 15:00
 **/
public interface Observer {

    void update(float temp, float humidity, float pressure);
}
