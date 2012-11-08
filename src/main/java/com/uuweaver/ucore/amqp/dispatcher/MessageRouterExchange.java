package com.uuweaver.ucore.amqp.dispatcher;


import com.uuweaver.ucore.amqp.core.FanoutExchange;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/11/12
 * Time: 8:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessageRouterExchange extends FanoutExchange {
    public MessageRouterExchange(){
        super("message_router", true, false, null);
    }
}
