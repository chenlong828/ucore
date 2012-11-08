package com.uuweaver.ucore.rest.dispatch;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/7/12
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractRESTResult<E extends Serializable> {

    private boolean handled = true;

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public abstract String toHttpResponse();

}
