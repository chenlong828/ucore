package com.uuweaver.ucore.amqp.dispatcher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.uuweaver.ucore.amqp.interfaces.DispatchExchange;
import com.uuweaver.ucore.amqp.interfaces.MessageRoute;
import com.uuweaver.ucore.util.SpringContextUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AMQP消息处理分发配置工具，根据每个消息处理cmdlet的annotation进行配置。
 * User: chenlong828
 * Date: 10/10/12
 * Time: 8:02 PM
 */
public class AMQPDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(AMQPDispatcher.class);

    private ConnectionFactory connectionFactory;

    private ExecutorService executorService;

    private Connection conn;

    private ChannelManager channelManager;

    //private List<DispatchExchange> dispatchExchangeList;

    private int concurrentAMQP;

    private int concurrentThread;

    public AMQPDispatcher() {

    }

    public void setConcurrentAMQP(int value)
    {
        this.concurrentAMQP = value;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setChannelManager(ChannelManager channelManager) {
        this.channelManager = channelManager;
    }

    public void setDispatchExchangeList(List<DispatchExchange> dispatchExchangeList) {
        //this.dispatchExchangeList = dispatchExchangeList;
    }

    public void init() throws IOException, ClassNotFoundException {
        logger.info("Starting init MessageDispatcher...");

        executorService = Executors.newFixedThreadPool(concurrentThread);
        conn = connectionFactory.newConnection(executorService);

        channelManager.initChannel(conn);

        configAMQPCmdlet();
    }


    private void configAMQPCmdlet() throws IOException {

        Reflections reflections = SpringContextUtils.getBean("reflections", Reflections.class);
        Set<Class<?>> annotateClazzs = reflections.getTypesAnnotatedWith(MessageRoute.class);
        for(Class clazz : annotateClazzs) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof MessageRoute) {
                    try {
                        registerMessageCmdlet(clazz.getName(), (MessageRoute) annotation);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void clean() throws IOException {
        channelManager.closeChannel();
        conn.close();
        executorService.shutdown();
    }



    private void registerMessageCmdlet(String className, MessageRoute mr)
            throws IOException, ClassNotFoundException, InvalidParameterException {
        String queueName = "queue." + mr.RouteKey();


        logger.debug("Attaching message RouteKey {} with cmdlet: {}", mr.RouteKey(), className);

        //declare the queue as non-durable, non-exclusive, auto-delete and without detail configuration.


        AbstractMessageCmdlet cmdlet = (AbstractMessageCmdlet) SpringContextUtils.getBeanByClassName(className);




        for(int i = 0; i < mr.ConsumerCount(); i++)
        {
            Channel channel = channelManager.addDynamicChannel();
            channel.queueDeclare(queueName, false, false, true, null);
            channel.queueBind(queueName, mr.SourceExchange(), mr.RouteKey());
            cmdlet.setChannel(channel);
            channel.basicConsume(queueName, cmdlet.getCallback());
        }


    }
}
