package com.uuweaver.ucore.amqp.dispatcher;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * AMQP消息接收抽象类 User: chenlong828 Date: 10/11/12 Time: 7:56 PM
 */
public abstract class AbstractMessageCmdlet {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractMessageCmdlet.class);

	private MessageConsumer messageConsumer;

	public void setChannel(Channel channel) {
		messageConsumer = new MessageConsumer(channel);
	}

	public MessageConsumer getCallback() {
		return messageConsumer;
	}

	public class MessageConsumer extends DefaultConsumer {
		private Channel myChannel;

		private MessageConsumer(Channel channel) {
			super(channel);
			myChannel = channel;
		}

		@Override
		public void handleDelivery(String consumerTag, Envelope envelope,
				AMQP.BasicProperties properties, byte[] body)
				throws IOException {
			try {
				String msg = new String(body, "UTF-8");
				logger.info(
						"Receiving Message: exchange={}, routingKey={}, message={}",
						envelope.getExchange(), envelope.getRoutingKey(), msg);

				boolean result = processMessage(consumerTag, envelope, msg);
				if (result) {
					myChannel.basicAck(envelope.getDeliveryTag(), false);
				}
			} catch (Exception e) {
				logger.error("handleDelivery error info is " + e);
			}
		}
	}

	protected abstract boolean processMessage(String tag, Envelope env,
			String msg);
}
