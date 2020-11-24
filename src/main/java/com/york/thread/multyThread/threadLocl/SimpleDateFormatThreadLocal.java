package com.york.thread.multyThread.threadLocl;

import java.text.SimpleDateFormat;

/**
 * @author york
 * @create 2020-11-24 10:06
 **/
public class SimpleDateFormatThreadLocal extends ThreadLocal<SimpleDateFormat> {

    private String pattern;

    public SimpleDateFormatThreadLocal(String pattern) {
        super();
        this.pattern = pattern;
    }
    @Override
    protected SimpleDateFormat initialValue() {
        return new SimpleDateFormat(pattern);
    }
}
