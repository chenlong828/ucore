package com.uuweaver.ucore.amqp.impl;

import com.uuweaver.ucore.amqp.core.Binding;
import com.uuweaver.ucore.amqp.core.ConsumeInfo;
import com.uuweaver.ucore.amqp.core.Exchange;
import com.uuweaver.ucore.amqp.core.Queue;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ChengZhendong
 * Date: 10/11/12 4:36 下午
 */
public class ExchangeMsgConsumer {

    protected Connection connection;

    protected Channel channel;

    protected Exchange exchange;

    protected Queue queue;

    protected Binding binding;

    protected MsgHandler msgHandler;

    protected ConsumeInfo consumeInfo;

    protected void init() throws IOException {
        createChannel();
        declareExchange();
        declareQueue();
        bindQueue();
        createConsumer();
    }

    protected void createChannel() throws IOException {
        // 创建channel
        this.channel = connection.createChannel();
    }

    protected void declareExchange() throws IOException {
        this.channel.exchangeDeclare(exchange.getName(), exchange.getType(),
                exchange.isDurable(), exchange.isAutoDelete(), exchange.getArguments());
    }

    protected void declareQueue() throws IOException {
        this.channel.queueDeclare(queue.getName(), queue.isDurable(), queue.isExclusive(), queue.isAutoDelete(), queue.getArguments());
    }

    protected void bindQueue() throws IOException {
        this.channel.queueBind(binding.getDestination(), binding.getExchange(), binding.getRoutingKey(), binding.getArguments());
    }

    protected void createConsumer() throws IOException {
        this.channel.basicConsume(this.queue.getName(), consumeInfo.isAutoAck(),
                consumeInfo.getConsumerTag(), consumeInfo.isNoLocal(), consumeInfo.isExclusive(),
                consumeInfo.getArguments(), new BasicMsgConsumer(this.channel));
    }


    private class BasicMsgConsumer extends DefaultConsumer {

        public BasicMsgConsumer(Channel channel) {
            super(channel);

        }

        @Override
        public void handleDelivery(String consumerTag,
                                   Envelope envelope,
                                   AMQP.BasicProperties properties,
                                   byte[] body) {
            System.out.println(envelope.getRoutingKey());
            System.out.println(new String(body));
            boolean flag = msgHandler.handle(new String(body));
            try {
                if (!consumeInfo.isAutoAck()) {
                    if (flag) {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } else {
                        channel.basicNack(envelope.getDeliveryTag(), false, true);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
