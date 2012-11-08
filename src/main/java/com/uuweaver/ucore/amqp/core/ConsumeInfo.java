package com.uuweaver.ucore.amqp.core;

import java.util.Map;

/**
 * Consume的配置可选属性
 * User: ChengZhendong
 * Date: 9/15/12 1:37 下午
 */
public class ConsumeInfo {

    public static final boolean DEFAULT_AUTO_ACK = false;

    public static final String DEFAULT_CONSUMER_TAG = "";

    public static final boolean DEFAULT_NO_LOCAL = false;

    public static final boolean DEFAULT_EXCLUSIVE = false;

    public static final Map<String, Object> DEFAULT_ARGUMENTS = null;

    public boolean autoAck = DEFAULT_AUTO_ACK;

    public String consumerTag = DEFAULT_CONSUMER_TAG;

    public boolean noLocal = DEFAULT_NO_LOCAL;

    public boolean exclusive = DEFAULT_EXCLUSIVE;

    public Map<String, Object> arguments = null;

    public ConsumeInfo() {
    }

    public ConsumeInfo(boolean autoAck) {
        this.autoAck = autoAck;
    }

    public ConsumeInfo(boolean autoAck, String consumerTag) {
        this.autoAck = autoAck;
        this.consumerTag = consumerTag;
    }

    public ConsumeInfo(boolean autoAck, String consumerTag, boolean noLocal, boolean exclusive, Map<String, Object> arguments) {
        this.autoAck = autoAck;
        this.consumerTag = consumerTag;
        this.noLocal = noLocal;
        this.exclusive = exclusive;
        this.arguments = arguments;
    }

    public boolean isAutoAck() {
        return autoAck;
    }

    public void setAutoAck(boolean autoAck) {
        this.autoAck = autoAck;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public void setConsumerTag(String consumerTag) {
        this.consumerTag = consumerTag;
    }

    public boolean isNoLocal() {
        return noLocal;
    }

    public void setNoLocal(boolean noLocal) {
        this.noLocal = noLocal;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

}
