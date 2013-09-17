package com.uuweaver.ucore.amqp.dispatcher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ChenLong
 * Created Date: 7/16/13 11:45 上午
 * Description:
 */
public class ChannelManager {


    private List<Channel> channelPool;


    private Connection connection;



    public ChannelManager()
    {

        channelPool = new ArrayList<Channel>();
    }

    public Channel addDynamicChannel() throws IOException {
        Channel channel = connection.createChannel();
        configExchange(channel);
        channelPool.add(channel);
        return channel;
    }

    public void initChannel(Connection conn) throws IOException, ClassNotFoundException {
        connection = conn;
    }


    public void closeChannel() throws IOException {

        for(Channel channel: channelPool)
        {
            channel.close();
        }
    }


    private void configExchange(Channel channel) throws IOException {
        MessageRouterExchange mrExchange = new MessageRouterExchange();

        // create exchange
        channel.exchangeDeclare(mrExchange.getName(), mrExchange.getType(), mrExchange.isDurable(),
                mrExchange.isAutoDelete(), mrExchange.getArguments());

        // create topic& bind topic to fanout
//        for (DispatchExchange exchange : dispatchExchangeList) {
//            channel.exchangeDeclare(exchange.getName(), exchange.getType(), exchange.isDurable(),
//                    exchange.isAutoDelete(), exchange.getArguments());
//
//            channel.exchangeBind(exchange.getName(), route_exchange.getName(), exchange.getRouteKey());
//        }
    }


}
