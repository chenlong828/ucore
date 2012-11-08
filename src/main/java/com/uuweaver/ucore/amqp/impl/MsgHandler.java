package com.uuweaver.ucore.amqp.impl;

/**
 * Created with IntelliJ IDEA.
 * User: ChengZhendong
 * Date: 10/11/12 4:55 下午
 */
public interface MsgHandler {

    public boolean handle(String message);
}
