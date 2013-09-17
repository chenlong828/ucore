package com.uuweaver.ucore.amqp.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/10/12
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageRoute {

    String RouteKey();

    String SourceExchange();

    int ConsumerCount() default 9;

    MessageQoS QoS() default MessageQoS.Normal;
}
