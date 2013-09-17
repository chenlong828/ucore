package com.uuweaver.ucore.amqp.dispatcher;


import com.uuweaver.ucore.amqp.core.TopicExchange;

/**
 * Exchange名为message_router的TopicExchange
 * User: chenlong828
 * Date: 10/11/12
 * Time: 8:52 PM
 */
public class MessageRouterExchange extends TopicExchange {
    public MessageRouterExchange(){
        super("message_router", true, false, null);
    }
}
