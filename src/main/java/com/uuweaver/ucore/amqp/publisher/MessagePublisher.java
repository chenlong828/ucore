package com.uuweaver.ucore.amqp.publisher;

import com.uuweaver.ucore.amqp.dispatcher.MessageRouterExchange;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: chenlong828
 * Date: 10/12/12
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MessagePublisher {

    private static transient Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    private ConnectionFactory connectionFactory;

    private ExecutorService executor_service;

    private Connection conn;

    private Channel channel;

    private int concurrentThread;

    private boolean enabled;

    private MessageRouterExchange router_exchange;

    public MessagePublisher()
    {
        router_exchange = new MessageRouterExchange();
        enabled = false;
    }

    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void InitConfig() throws IOException {

        if (!enabled) return;

        logger.info("Starting init MessagePublisher...");
        executor_service = Executors.newFixedThreadPool(concurrentThread);
        conn = connectionFactory.newConnection(executor_service);

        channel = conn.createChannel();

        channel.exchangeDeclare(router_exchange.getName(), router_exchange.getType(), router_exchange.isDurable(),
                router_exchange.isAutoDelete(), router_exchange.getArguments());

        SendMessage("hello", "hello".getBytes());
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void CleanConfig() throws IOException {
        channel.close();
        conn.close();
        executor_service.shutdown();
    }

    public boolean SendMessage(String route_key, byte[] body) {
        try {
            channel.basicPublish(router_exchange.getName(), route_key, null, body);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
