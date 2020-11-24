package com.york.thread.designTemplate.observer;

/**
 * @author york
 * @create 2020-03-26 15:00
 **/
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
