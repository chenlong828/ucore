package com.uuweaver.ucore.amqp.interfaces;

import com.uuweaver.ucore.amqp.core.TopicExchange;

import java.util.Map;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-10-12
 * Time: 下午1:33
 */
public class DispatchExchange extends TopicExchange {
    public DispatchExchange(String name) {
        super(name);
    }

    public DispatchExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }

    public DispatchExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }

    private String routeKey;

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
