package com.uuweaver.ucore.amqp.interfaces;

/**
 * User: ChenLong
 * Created Date: 7/16/13 11:14 上午
 * Description:
 */
public enum MessageQoS {
    Low(1),
    Normal(2),
    High(3);

    private int value;

    MessageQoS(int i)
    {
        this.value = i;
    }
}
