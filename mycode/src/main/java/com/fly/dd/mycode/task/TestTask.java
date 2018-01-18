package com.fly.dd.mycode.task;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * Created by zhuyd on 2017/3/28.
 */
@Component
public class TestTask {

    public TestTask() {
        System.out.println("我被初始化了1");
    }

    public void test1() {
        System.out.println(LocalDateTime.now() + "我每10秒执行一次");
    }

    public void test2() {
        System.out.println(LocalDateTime.now() + "我每五分钟执行一次");
    }

}
