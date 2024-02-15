package com.stu.manage.entity;

/**
 * @author: Sparks
 * @Date: 2024/1/21 14:14
 * @Description
 */
public class StatusResult {

    private int status;

    private String message;

    public int getStatus() {
        return status;
    }

    public StatusResult() {
    }

    public StatusResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public StatusResult setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public StatusResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
