package com.york.thread.reflect;

/**
 * @author york
 * @create 2020-04-09 17:53
 **/
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c1 = Class.forName("com.york.thread.reflect.Employee");
        Class c2 = Employee.class;
        Employee employee = new Employee("小明", "18", "写代码", 1, "Java攻城狮", 100000);
        Class c3 = employee.getClass();    // 第3种，通过调用对象的getClass()方法获取Class对象
        if (c1 == c2 && c1 == c3) {
            System.out.println("c1,c2,c3为同一个对象");
            System.out.println(c1);
        }
    }
}
