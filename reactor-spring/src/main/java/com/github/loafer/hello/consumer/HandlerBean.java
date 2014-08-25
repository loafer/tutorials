package com.github.loafer.hello.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

/**
 * @author zhaojh
 */
@Consumer
public class HandlerBean {
    private static final Logger logger = LoggerFactory.getLogger(HandlerBean.class);

    @Selector(value = "english", reactor = "@rootReactor")
    public void english(String s){
        logger.info("Hello, {}", s);
    }

    @Selector(value = "chinese", reactor = "@rootReactor")
    public void chinese(Event<String> event){
        logger.info("你好， {}", event.getData());
    }
}
