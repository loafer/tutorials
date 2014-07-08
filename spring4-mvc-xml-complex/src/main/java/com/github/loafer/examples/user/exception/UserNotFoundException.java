package com.github.loafer.examples.user.exception;

/**
 * @author zhaojh
 */
public class UserNotFoundException extends RuntimeException{
    private final String name;

    public UserNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
