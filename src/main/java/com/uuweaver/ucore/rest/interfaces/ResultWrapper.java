package com.uuweaver.ucore.rest.interfaces;

import java.io.Serializable;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-10-8
 * Time: 上午11:03
 */
public class ResultWrapper implements Serializable {
    private int statusCode;

    private Object returnObj;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }
}
