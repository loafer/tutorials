package com.github.loafer.examples.user.web;

/**
 * @author zhaojh
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
