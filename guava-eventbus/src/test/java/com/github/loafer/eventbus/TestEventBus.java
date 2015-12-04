package com.github.loafer.eventbus;

import com.github.loafer.eventbus.event.HelloEvent;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author zhaojh
 */
public class TestEventBus {

    @Test
    public void testHelloEvent(){
        EventBus eventBus = new EventBus("hello");
        HelloEventListener listener = new HelloEventListener();

        eventBus.register(listener);

        HelloEvent event = new HelloEvent("hello world.");
        eventBus.post(event);
    }
}
