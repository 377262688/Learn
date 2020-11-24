package com.york.thread.designTemplate.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author york
 * @create 2020-03-26 15:14
 **/
public class StatisticsDisplay implements Observer,DisplayElement {
    private Observable observable;
    private float temperature;
    private float humidity;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }


    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemp();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
