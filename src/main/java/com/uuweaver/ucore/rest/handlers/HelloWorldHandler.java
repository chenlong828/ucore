package com.uuweaver.ucore.rest.handlers;

import com.uuweaver.ucore.rest.dispatch.AbstractRESTCmdlet;
import com.uuweaver.ucore.rest.dispatch.AbstractRESTResult;
import com.uuweaver.ucore.rest.dispatch.RESTAnnotate;
import com.uuweaver.ucore.rest.interfaces.PlainTextRESTResult;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.springframework.stereotype.Controller;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-10-8
 * Time: 上午9:35
 */
@Controller
@RESTAnnotate(URL = "/helloworld/*", Methods = HttpMethods.GET)
public class HelloWorldHandler extends AbstractRESTCmdlet {
    @Override
    public AbstractRESTResult Execute(Request request) {
        PlainTextRESTResult result = new PlainTextRESTResult();
        //result.getWriter().write("hello,world");

        return result;
    }
}
