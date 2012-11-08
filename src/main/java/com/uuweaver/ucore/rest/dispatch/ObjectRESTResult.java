package com.uuweaver.ucore.rest.dispatch;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/7/12
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ObjectRESTResult extends AbstractRESTResult {

    protected int statusCode;

    protected Object returnObj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected String msg;

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

    protected abstract String convertObjToHttpResponse();

    @Override
    public String toHttpResponse() {
        return convertObjToHttpResponse();
    }
}
