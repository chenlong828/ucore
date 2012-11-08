package com.uuweaver.ucore.amqp.dispatcher;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/11/12
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMessageCmdlet {

    private MessageConsumer consumer;
    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
        consumer = new MessageConsumer(channel);
    }

    public MessageConsumer getCallback()
    {
        return consumer;
    }

    private class MessageConsumer extends DefaultConsumer
    {
        private MessageConsumer(Channel channel) {
            super(channel);
        }

        @Override
        public void handleDelivery(String consumerTag,
                                   Envelope envelope,
                                   AMQP.BasicProperties properties,
                                   byte[] body) throws IOException {
            boolean result = DoProcessMessage(consumerTag, envelope, body);
            if (result)
            {
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        }
    }

    protected abstract boolean DoProcessMessage(String tag, Envelope env, byte[] body);
}
