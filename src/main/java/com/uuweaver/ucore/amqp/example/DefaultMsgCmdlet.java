package com.uuweaver.ucore.amqp.example;

import com.uuweaver.ucore.amqp.dispatcher.AbstractMessageCmdlet;
import com.uuweaver.ucore.amqp.interfaces.AMQPExchanges;
import com.uuweaver.ucore.amqp.interfaces.MessageRoute;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description： 消息处理的样例程序
 * Author: ChenLong
 * Date: 12-10-12
 * Time: 上午8:30
 */
@Component
@MessageRoute(RouteKey="*", SourceExchange = AMQPExchanges.MR_Exchange)
public class DefaultMsgCmdlet extends AbstractMessageCmdlet {

    private static transient Logger logger = LoggerFactory.getLogger(AbstractMessageCmdlet.class);

    @Override
    protected boolean DoProcessMessage(String tag, Envelope env, byte[] body) {
        logger.info("Received {} message", env.getRoutingKey());
        logger.info("   content is: {}", new String(body));
        return true;
    }
}
