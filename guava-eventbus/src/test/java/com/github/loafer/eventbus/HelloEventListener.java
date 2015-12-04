package com.github.loafer.eventbus;

import com.github.loafer.eventbus.event.HelloEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author zhaojh
 */
public class HelloEventListener {
    @Subscribe
    public void sayHello(HelloEvent event){
        System.out.println(event.getMessage());
    }
}
