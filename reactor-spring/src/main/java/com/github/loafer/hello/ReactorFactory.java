package com.github.loafer.hello;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

/**
 * @author zhaojh
 */
public class ReactorFactory {
    public static Reactor createReactor(){
        return Reactors.reactor().env(new Environment()).get();
    }
}
