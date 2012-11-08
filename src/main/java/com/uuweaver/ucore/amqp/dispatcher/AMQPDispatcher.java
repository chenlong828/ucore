package com.uuweaver.ucore.amqp.dispatcher;

import com.uuweaver.ucore.amqp.interfaces.DispatchExchange;
import com.uuweaver.ucore.amqp.interfaces.MessageRoute;
import com.uuweaver.ucore.util.PkgScanner;
import com.uuweaver.ucore.util.SpringContextUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: AMQP消息处理分发配置工具，根据每个消息处理cmdlet的annotation进行配置。
 * User: chenlong828
 * Date: 10/10/12
 * Time: 8:02 PM
 */
public class AMQPDispatcher {

    private static transient Logger logger = LoggerFactory.getLogger(AMQPDispatcher.class);

    private ConnectionFactory connectionFactory;

    private ExecutorService executor_service;

    private Connection conn;

    private Channel channel;

    private List<String> packageList;

    private List<DispatchExchange> dispatchExchangeList;

    private int concurrentThread;

    private boolean enabled;

    public AMQPDispatcher()
    {
        enabled = false;
    }

    public void setPackageList(List<String> package_list) {
        packageList = package_list;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setDispatchExchangeList(List<DispatchExchange> dispatchExchangeList) {
        this.dispatchExchangeList = dispatchExchangeList;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void InitConfig() throws IOException {

        if (!enabled) return;

        logger.info("Starting init MessageDispatcher...");

        executor_service = Executors.newFixedThreadPool(concurrentThread);
        conn = connectionFactory.newConnection(executor_service);

        channel = conn.createChannel();

        ConfigExchange(channel);

        for(String package_name: packageList)
        {
            List<String> class_list = PkgScanner.getClassInPackage(package_name);
            for(String class_name: class_list)
            {
                try {
                    Annotation[] annotations = Class.forName(class_name).getAnnotations();
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof MessageRoute) {
                            RegisterMessageCmdlet(channel, class_name, (MessageRoute) annotation);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void CleanConfig() throws IOException {

        channel.close();
        conn.close();
        executor_service.shutdown();
    }

    private void RegisterMessageCmdlet(Channel channel, String class_name, MessageRoute mr)
            throws IOException, ClassNotFoundException {
        String queue_name = "queue." + mr.RouteKey();

        logger.info("Attaching message RouteKey {} with cmdlet: {}", mr.RouteKey(), class_name);

        //declare the queue as non-durable, non-exclusive, auto-delete and without detail configuration.
        channel.queueDeclare(queue_name, false, false, true,null);
        channel.queueBind(queue_name,mr.SourceExchange(), mr.RouteKey());

        AbstractMessageCmdlet cmdlet = (AbstractMessageCmdlet) SpringContextUtil.getBeanByClassName(class_name);
        cmdlet.setChannel(channel);
        channel.basicConsume(queue_name, cmdlet.getCallback());
    }


    private void ConfigExchange(Channel channel) throws IOException {
        MessageRouterExchange route_exchange = new MessageRouterExchange();

        channel.exchangeDeclare(route_exchange.getName(), route_exchange.getType(), route_exchange.isDurable(),
                route_exchange.isAutoDelete(), route_exchange.getArguments());


        for(DispatchExchange exchange: dispatchExchangeList)
        {
            channel.exchangeDeclare(exchange.getName(), exchange.getType(), exchange.isDurable(),
                    exchange.isAutoDelete(), exchange.getArguments());
            channel.exchangeBind(exchange.getName(), route_exchange.getName(), exchange.getRouteKey());
        }
    }


}
