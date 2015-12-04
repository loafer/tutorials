package com.github.loafer.hello;

import com.github.loafer.hello.consumer.HelloConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;
import reactor.function.Function;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhaojh
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = HelloApplication.class)
@ContextConfiguration({"/spring-config.xml"})
public class ReactorTest {
    private static final Logger logger = LoggerFactory.getLogger(ReactorTest.class);
    @Resource
    private Reactor rootReactor;
    @Resource
    private HelloConsumer helloConsumer;

    private Reactor notify(String key, Event<String> event) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Reactor r= rootReactor.notify(key, event, new Consumer<Event<String>>() {
            @Override
            public void accept(Event<String> event) {
                logger.info("{} complete: [data: {}, replyTo: {}]", event.getKey(), event.getData(), event.getReplyTo());
                latch.countDown();
            }
        });

        latch.await();
        return r;
    }

    @Test
    public void test() throws InterruptedException {
        rootReactor.on(Selectors.$("HelloEvent"), helloConsumer);
        notify("HelloEvent", Event.wrap("zhaojh"));
    }

    @Test
    public void testHelloEnglish() throws InterruptedException {
        notify("english", Event.wrap("zhaojh"));
    }

    @Test
    public void testHelloChinese() throws InterruptedException {
        notify("chinese", Event.wrap("zhaojh"));
    }

    @Test
    public void testReplyInEnglish() throws InterruptedException {
        notify("english", Event.wrap("zhaojh"));
    }

    @Test
    public void testReplyInChinese() throws InterruptedException {
        notify("chinese", Event.wrap("zhaojh").setReplyTo("replyInChinese"));
    }

    @Test
    public void testReactorReceive() throws InterruptedException {
        rootReactor.on(Selectors.$("channel2"), new Consumer<Event<String>>() {
            @Override
            public void accept(Event<String> event) {
                logger.info("accept: {}", event.getData());
            }
        });

        rootReactor.receive(Selectors.$("channel1"), new Function<Event<String>, String>() {
            @Override
            public String apply(Event<String> event) {
                return "Thank you! " + event.getData();
            }
        });

        notify("channel1", Event.wrap("zhaojh").setReplyTo("channel2"));
    }
}
