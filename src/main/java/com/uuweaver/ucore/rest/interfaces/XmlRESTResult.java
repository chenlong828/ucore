package com.uuweaver.ucore.rest.interfaces;

import com.uuweaver.ucore.rest.dispatch.ObjectRESTResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/7/12
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class XmlRESTResult extends ObjectRESTResult {

    @Override
    protected String convertObjToHttpResponse() {
        throw new NotImplementedException();
    }
}
