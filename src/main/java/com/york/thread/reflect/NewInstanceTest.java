package com.york.thread.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author york
 * @create 2020-04-09 17:57
 **/
public class NewInstanceTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c = Date.class;
        Date date = (Date) c.newInstance();
        System.out.println(date);

        Constructor constructor = c.getConstructor(long.class);
        Date date1 = (Date) constructor.newInstance(date.getTime());
        System.out.println(date1);

        Class e = Employee.class;
        Constructor[] constructors = e.getDeclaredConstructors();
        System.out.println("Employee所有的构造方法：");
        for (Constructor constructor1 : constructors) {
            System.out.println(constructor1.getModifiers());
            System.out.println(Modifier.isAbstract(constructor.getModifiers()));
        }
        System.out.println(e.getDeclaredConstructors());
    }
}
