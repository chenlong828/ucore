package com.uuweaver.ucore.rest.interfaces;

import com.uuweaver.ucore.rest.dispatch.ObjectRESTResult;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/7/12
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlainTextRESTResult extends ObjectRESTResult {

    private StringWriter outputWriter;

    public PlainTextRESTResult()
    {
        outputWriter = new StringWriter(1024);
    }

    public Writer getWriter() {
        return outputWriter;
    }

    @Override
    protected String convertObjToHttpResponse() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String toHttpResponse() {
        return outputWriter.toString();
    }
}
