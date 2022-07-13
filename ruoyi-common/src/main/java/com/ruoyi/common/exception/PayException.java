package com.ruoyi.common.exception;

public class PayException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String msg;

    public PayException() {
    }

    public PayException(String msg) {
        this.msg = msg;
    }
}
