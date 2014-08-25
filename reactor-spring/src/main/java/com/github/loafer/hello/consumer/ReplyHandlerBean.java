package com.github.loafer.hello.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.ReplyTo;
import reactor.spring.context.annotation.Selector;

/**
 * @author zhaojh
 */
@Consumer
public class ReplyHandlerBean {
    private static final Logger logger = LoggerFactory.getLogger(ReplyHandlerBean.class);

    @Selector(value = "english", reactor = "@rootReactor")
    @ReplyTo("replyInEnglish")
    public String english(String value){
        logger.info("english accept: {}", value);
        return value;
    }

    @Selector(value = "chinese", reactor = "@rootReactor")
    @ReplyTo
    public String chinese(String value){
        logger.info("chinese accept: {}", value);
        return value;
    }
}
