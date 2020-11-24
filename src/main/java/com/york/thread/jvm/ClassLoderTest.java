package com.york.thread.jvm;

/**
 * @author york
 * @create 2020-03-16 09:16
 **/
public class ClassLoderTest extends ClassLoader {

    ClassLoader classLoader;

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
    }

    @Override
    public Class<?> findClass(String name) {
        return defineClass(name,new byte[1024],0,1024);
    }
}
