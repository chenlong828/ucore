package com.uuweaver.ucore.webservice;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * User: ChenLong
 * Created Date: 8/22/13 5:46 下午
 * Description:
 */
@WebService(
        serviceName = "DebugWebServiceImpl",
        targetNamespace = "/service/DebugWebService",
        endpointInterface = "com.uuweaver.ucore.webservice.DebugWebService"
)
@Service("debugWebService")
public class DebugWebServiceImpl implements  DebugWebService{


    public DebugWebServiceImpl(){
        //throw new Exception();
    }

    @Override
    @WebMethod
    public String Debug(String input) {
        return "Debug webservice:" + input;
    }
}
