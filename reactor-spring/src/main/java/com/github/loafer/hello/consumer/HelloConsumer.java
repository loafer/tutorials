package com.github.loafer.hello.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.event.Event;
import reactor.function.Consumer;

/**
 * @author zhaojh
 */
@Component
public class HelloConsumer implements Consumer<Event<String>>{
    private static final Logger logger = LoggerFactory.getLogger(HelloConsumer.class);

    @Override
    public void accept(Event<String> event) {
        logger.info("{} handler accept: {}", event.getKey(), event.getData());
        event.setData("Hello " + event.getData());
    }
}
