package com.github.loafer.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date Created  14-3-17
 *
 * @author zjh
 */
public class HelloWorld {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        logger.debug("Hello World");
    }
}
