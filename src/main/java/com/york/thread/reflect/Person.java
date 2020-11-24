package com.york.thread.reflect;

/**
 * @author york
 * @create 2020-04-09 17:49
 **/
public class Person {

    public String name;

    protected String age;

    private String hobby;

    public Person(String name,String age,String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public String getHobby() {
        return hobby;
    }
}
