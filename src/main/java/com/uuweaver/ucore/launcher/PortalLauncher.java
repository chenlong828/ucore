

/**
 * Portal后台执行的主程序入口，主要初始化Spring、Jetty和AMQP组件，为业务模块加载
 * 提供完整的模块
 * User: ChenLong
 * Date: 12-9-7
 * Time: 上午8:48
 * To change this template use File | Settings | File Templates.
 */
package com.uuweaver.ucore.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PortalLauncher {
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");


//        ICoreService core_service = context.getBean("core_service", ICoreService.class);
//        core_service.setContext(context);

    }
}
