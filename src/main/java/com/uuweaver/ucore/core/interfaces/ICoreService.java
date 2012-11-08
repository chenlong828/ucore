package com.uuweaver.ucore.core.interfaces;

import org.springframework.context.ApplicationContext;

/**
 * Description：
 * Author: ChenLong
 * Date: 12-9-7
 * Time: 下午4:16
 */
public interface ICoreService {
    ApplicationContext getContext();
    void setContext(ApplicationContext ctx);

    Object getBean(String bean_name);

    <T> T getBean(String bean_name, Class<T> t);
}
