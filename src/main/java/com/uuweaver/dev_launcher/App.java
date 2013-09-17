package com.uuweaver.dev_launcher;

import com.uuweaver.ucore.util.SpringContextUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-config.xml");

        SpringContextUtils util = context.getBean(SpringContextUtils.class);
        util.setApplicationContext(context);

        System.out.println("----------------->start server end!!!");
    }
}
