package com.uuweaver.ucore.amqp.interfaces;

/**
 * Description: 用于Topic Message的包装类，用于在申明时包装消息队列客户端信息
 * User: chenlong828
 * Date: 10/1/12
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopicHandlerConfigItem {

    private IMessageHandler messageHandler;

    private String messageTopic;

    public TopicHandlerConfigItem(String msg_topic, IMessageHandler msg_handler)
    {
        messageHandler = msg_handler;
        messageTopic = msg_topic;
    }
}
