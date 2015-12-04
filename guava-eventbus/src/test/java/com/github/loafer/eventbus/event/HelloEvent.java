package com.github.loafer.eventbus.event;

/**
 * @author zhaojh
 */
public class HelloEvent {
    private String message;

    public HelloEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
