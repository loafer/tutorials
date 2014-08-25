package com.github.loafer.hello.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

/**
 * @author zhaojh
 */
@Consumer
public class ResultHandlerBean {
    private static final Logger logger = LoggerFactory.getLogger(ResultHandlerBean.class);

    @Selector(value = "replyInEnglish", reactor = "@rootReactor")
    public void speakEnglish(String value){
        logger.info("Hello, {}", value);
    }

    @Selector(value = "replyInChinese", reactor = "@rootReactor")
    public void speakChinese(String value){
        logger.info("你好，{}", value);
    }
}
