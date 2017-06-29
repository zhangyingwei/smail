package com.zhangyingwei.smail.exception;

/**
 * Created by zhangyw on 2017/6/29.
 */
public class SmailException extends Exception {
    public SmailException() {
        super();
    }

    public SmailException(String message) {
        super(message);
    }

    public SmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmailException(Throwable cause) {
        super(cause);
    }

    protected SmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
