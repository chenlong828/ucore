package com.uuweaver.ucore.rest.handlers;

import com.uuweaver.ucore.rest.dispatch.AbstractRESTCmdlet;
import com.uuweaver.ucore.rest.dispatch.AbstractRESTResult;
import com.uuweaver.ucore.rest.dispatch.RESTAnnotate;
import com.uuweaver.ucore.rest.interfaces.PlainTextRESTResult;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * Description: 测试用于REST的Cmdlet模式的接口类
 * User: chenlong828
 * Date: 10/7/12
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RESTAnnotate(URL="/*", Methods=HttpMethods.GET)
public class DebugRESTCmdlet extends AbstractRESTCmdlet {

    @Resource
    private HelloWorldHandler helloWorldHandler;

    @Override
    public AbstractRESTResult Execute(Request request){

        PlainTextRESTResult result = new PlainTextRESTResult();
        Writer response_writer = result.getWriter();

        try {
            response_writer.write(String.format("Request URI is :%s \n", request.getRequestURI()));

            Enumeration head_list = request.getHeaderNames();
            while(head_list.hasMoreElements())
            {
                String header_name = head_list.nextElement().toString();
                response_writer.write(String.format("%s: %s\n", header_name, request.getHeader(header_name)));
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new NotImplementedException();
        }

        result.setHandled(true);

        return result;
    }
}
