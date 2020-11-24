package com.york.thread.service.bingxingsousuo;

import lombok.Data;

/**
 * Created by york on 2019/3/29.
 */
@Data
public class Msg {

    private int a;
    private int b;
    public Msg(int a,int b) {
        this.a = a;
        this.b = b;
    }
}
