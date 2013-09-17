package com.uuweaver.ucore.amqp.interfaces;

import com.uuweaver.ucore.amqp.core.Exchange;
import com.uuweaver.ucore.amqp.core.TopicExchange;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-10-12
 * Time: 上午8:39
 */
public final class AMQPExchanges {
    public static final String MR_Exchange = "message_router";
    //public static final String IAM_Exchange = "iam_exchange";
    //public static final String Order_Exchange = "order_exchange";

    public static final Exchange DefaultConfig = new TopicExchange("def_config", true, false, null);
}
