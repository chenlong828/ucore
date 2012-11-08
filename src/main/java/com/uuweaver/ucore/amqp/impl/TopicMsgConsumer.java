package com.uuweaver.ucore.amqp.impl;

import com.uuweaver.ucore.amqp.core.Binding;
import com.uuweaver.ucore.amqp.core.ConsumeInfo;
import com.uuweaver.ucore.amqp.core.Queue;
import com.uuweaver.ucore.amqp.core.TopicExchange;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ChengZhendong
 * Date: 10/11/12 4:59 下午
 */
public class TopicMsgConsumer extends ExchangeMsgConsumer {

    public TopicMsgConsumer(String exchange, String queue, String routingkey) throws IOException {
        this(exchange, queue, routingkey, false);
    }

    public TopicMsgConsumer(String exchange, String queue, String routingkey, boolean autoAck) throws IOException {
        this.exchange = new TopicExchange(exchange);
        this.queue = new Queue(queue);
        this.binding = new Binding(queue, Binding.DestinationType.QUEUE, exchange, routingkey, null);
        this.consumeInfo = new ConsumeInfo(autoAck);
        init();
    }

}
