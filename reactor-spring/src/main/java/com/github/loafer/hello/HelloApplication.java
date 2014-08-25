package com.github.loafer.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * @author zhaojh
 */
@Configuration
@EnableReactor
@ComponentScan
public class HelloApplication {
    @Bean
    public Reactor rootReactor(Environment env){
        return Reactors.reactor().env(env).get();
    }
}
