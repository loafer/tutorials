package com.github.loafer.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date Created  14-3-17
 *
 * @author zjh
 */
public class HelloWorld2 {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger("com.github.loafer.logback.HelloWorld2");
        logger.debug("Hello World.");

        //print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
