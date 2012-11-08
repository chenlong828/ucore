package com.uuweaver.ucore.session;

import com.uuweaver.ucore.rest.interfaces.IRESTHandler;
import com.uuweaver.ucore.session.core.SessionManager;
import com.uuweaver.ucore.session.interfaces.BaseSessionInfo;
import com.uuweaver.ucore.util.SpringContextUtil;
import org.eclipse.jetty.server.Request;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description： 提供公共的Session服务
 *  所接受的REST接口：
 *      GET /session 创建Session并且返回给定的SessionID
 *      GET /session/{session_id}/{key}， 返回key对应的value
 *      POST /session/{session_id}/{key}/{value},设置key对应的value
 * Author: ChenLong
 * Date: 12-9-7
 * Time: 下午6:58
 */
public class SessionHandler implements IRESTHandler {

    private SessionManager session_manager;


    public SessionHandler()
    {
        session_manager = SpringContextUtil.getBean("session_manager", SessionManager.class);
    }

    @Override
    public boolean ExecuteDELETE(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public boolean ExecuteGET(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] args = request.getRequestURI().split("/");
        // 匹配URI的参数个数
        if (args.length != 2 && args.length != 4)
        {
            response.sendError(400);
        }
        if(args.length == 2) {
            if (args[1].equals("session"))
            {
                BaseSessionInfo session_info = session_manager.createNewSession();
                response.getWriter().println(session_info.getSessionID());
            }
        } else if(args.length == 4) {
            if (args[1].equals("session"))
            {
                String session_id = args[2];
                String key = args[3];
                String value = session_manager.getSessionValue(session_id, key);
                response.getWriter().println(value);
            }
            else
            {
                response.sendError(400);
            }
        }
        return true;
    }

    @Override
    public boolean ExecutePOST(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] args = request.getRequestURI().split("/");
        // 匹配URI的参数个数
        if (args.length != 5)
        {
            response.sendError(400);
        }
        if (args[1].equals("session"))
        {
            String session_id = args[2];
            String key = args[3];
            String value = args[4];
            session_manager.updateSessionValue(session_id, key, value);
        }
        return true;
    }

    @Override
    public boolean ExecuteHEAD(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public boolean ExecutePUT(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        throw new NotImplementedException();
    }
}
