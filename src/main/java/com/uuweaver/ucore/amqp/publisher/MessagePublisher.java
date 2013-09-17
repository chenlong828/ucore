package com.uuweaver.ucore.amqp.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.uuweaver.ucore.amqp.dispatcher.MessageRouterExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * User: chenlong828
 * Date: 10/12/12
 * Time: 10:22 PM
 */
public class MessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    private ConnectionFactory connectionFactory;

    private ExecutorService executor_service;

    private Connection conn;

    private Channel channel;

    private int concurrentThread;

    private MessageRouterExchange router_exchange;

    public MessagePublisher()
    {
        router_exchange = new MessageRouterExchange();
    }

    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void init() throws IOException {
        logger.info("Starting init MessagePublisher...");
        executor_service = Executors.newFixedThreadPool(concurrentThread);
        conn = connectionFactory.newConnection(executor_service);

        channel = conn.createChannel();

        channel.exchangeDeclare(router_exchange.getName(), router_exchange.getType(), router_exchange.isDurable(),
                router_exchange.isAutoDelete(), router_exchange.getArguments());
    }

    public void clean() throws IOException {
        channel.close();
        conn.close();
        executor_service.shutdown();
    }

    public boolean sendMessage(String routeKey, String body) {
        return sendMessage(routeKey, body.getBytes());
    }

    public boolean sendMessage(String routeKey, byte[] body) {
        try {
            channel.basicPublish(router_exchange.getName(), routeKey, null, body);
            logger.info("Sending Message: routingKey={}, message={}", routeKey, new String(body, "UTF-8"));
            return true;
        } catch (IOException e) {
            logger.error("Sending Message Error: routingKey={}, message={}, errorInfo={}", routeKey, new String(body), e);
            return false;
        }
    }
}
