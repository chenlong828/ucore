package com.uuweaver.ucore.rest.interfaces;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description： 实现REST接口的Handler，所有的REST请求将通过该Handler执行
 * Author: ChenLong
 * Date: 12-9-10
 * Time: 下午3:52
 */
public interface IRESTHandler {

    boolean ExecuteGET(String target, Request baseRequest, HttpServletRequest request,
                    HttpServletResponse response) throws IOException;
    boolean ExecutePOST(String target, Request baseRequest, HttpServletRequest request,
                     HttpServletResponse response) throws IOException;
    boolean ExecuteHEAD(String target, Request baseRequest, HttpServletRequest request,
                     HttpServletResponse response) throws IOException;
    boolean ExecutePUT(String target, Request baseRequest, HttpServletRequest request,
                    HttpServletResponse response) throws IOException;
    boolean ExecuteDELETE(String target, Request baseRequest, HttpServletRequest request,
                       HttpServletResponse response) throws IOException;
}
