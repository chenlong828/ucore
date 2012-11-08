package com.uuweaver.ucore.amqp.interfaces;

/**
 * Description： 消息处理接口
 * Author: ChenLong
 * Date: 12-9-13
 * Time: 下午6:28
 */
public interface IMessageHandler {
        boolean HandleTopicMessage(int msg_id, TopicMessageArgs msg_args);
}
