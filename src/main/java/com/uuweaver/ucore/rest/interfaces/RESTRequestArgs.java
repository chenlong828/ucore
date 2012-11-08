package com.uuweaver.ucore.rest.interfaces;

import org.eclipse.jetty.server.Request;

/**
 * Description: 实现对Restful请求时，对http参数的封装。
 * User: chenlong828
 * Date: 10/5/12
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class RESTRequestArgs {

    private Request request;

    public RESTRequestArgs(Request req)
    {
        request = req;
    }

    public String getRequestURI()
    {
        return request.getRequestURI();
    }

    public String getParameter(String param_name)
    {
        String result;
        String header_var;

        header_var = request.getHeader(param_name);
        if (header_var.equals(""))
        {
            result = request.getParameter(param_name);
            return result;
        }
        else
        {
            return header_var;
        }
    }

}
