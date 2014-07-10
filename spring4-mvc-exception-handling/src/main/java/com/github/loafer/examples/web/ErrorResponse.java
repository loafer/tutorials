package com.github.loafer.examples.web;

/**
 * Created by zhaojh on 14-7-3.
 */
public class ErrorResponse {
    private String url;
    private String message;

    public ErrorResponse(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
