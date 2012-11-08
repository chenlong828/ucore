package com.uuweaver.ucore.rest.dispatch;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/7/12
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RESTAnnotate {
    String URL();
    String Methods();
}
