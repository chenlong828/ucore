package com.uuweaver.ucore.rest.dispatch;

import org.eclipse.jetty.server.Request;

/**
 * Description: RESTcmdlet的执行抽象类，作为一切RESTCmdlet的执行根基
 * User: chenlong828
 * Date: 10/7/12
 * Time: 6:14 PM
 *
 */
public abstract class AbstractRESTCmdlet {

    public abstract AbstractRESTResult Execute(Request request);
}
