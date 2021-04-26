package com.jmb.batchapp.exception.model;

public class BatchAppException extends RuntimeException {

    public BatchAppException(String msg) {
        super(msg);
    }

    public BatchAppException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
