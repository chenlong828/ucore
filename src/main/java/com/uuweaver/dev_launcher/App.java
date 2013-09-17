package com.uuweaver.dev_launcher;

import com.uuweaver.ucore.util.SpringContextUtil;
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

        SpringContextUtil util = context.getBean(SpringContextUtil.class);
        util.setApplicationContext(context);
    }
}
