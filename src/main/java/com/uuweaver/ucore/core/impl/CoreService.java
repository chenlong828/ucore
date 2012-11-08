package com.uuweaver.ucore.core.impl;

import com.uuweaver.ucore.core.interfaces.ICoreService;
import org.springframework.context.ApplicationContext;

/**
 * Description： 主服务类,包含代码执行过程中的公共变量配置信息和公共配置服务
 * Author: ChenLong
 * Date: 12-9-7
 * Time: 下午4:17
 */
public class CoreService implements ICoreService {

    private ApplicationContext applicationContext;

    @Override
    public ApplicationContext getContext() {
        return applicationContext;
    }

    @Override
    public void setContext(ApplicationContext ctx) {
        this.applicationContext = ctx;
    }

    @Override
    public Object getBean(String bean_name) {
        return applicationContext.getBean(bean_name);
    }

    @Override
    public <T> T getBean(String bean_name, Class<T> t) {
        return applicationContext.getBean(bean_name, t);
    }
}
