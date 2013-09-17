package com.uuweaver.ucore.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * User: ChenLong
 * Created Date: 8/22/13 5:45 下午
 * Description:
 */
@WebService
public interface DebugWebService {

    @WebMethod
    String Debug(String input);
}
