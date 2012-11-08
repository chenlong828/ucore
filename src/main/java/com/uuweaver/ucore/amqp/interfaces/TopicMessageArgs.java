package com.uuweaver.ucore.amqp.interfaces;

/**
 * Description: 用于Topic Message接受时候封装消息的封装类
 * User: chenlong828
 * Date: 10/1/12
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopicMessageArgs {

    private String topic;

    private String messageBody;

    public TopicMessageArgs(String msg_topic, String msg_body)
    {
        topic = msg_topic;
        messageBody = msg_body;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
