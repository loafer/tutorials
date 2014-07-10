package com.github.loafer.examples.exception;

/**
 * @author zhaojh
 */
public class UserNotFoundException extends RuntimeException{
    private String name;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
