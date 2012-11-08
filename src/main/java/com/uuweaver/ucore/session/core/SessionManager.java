package com.uuweaver.ucore.session.core;

import com.uuweaver.ucore.session.interfaces.BaseSessionInfo;
import com.uuweaver.ucore.session.interfaces.ISessionManager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ChengZhenDong
 * Date: 9/10/12
 * Time: 11:47 上午
 */
public class SessionManager implements ISessionManager {

    private volatile Map<String, BaseSessionInfo> sessionInfos = null;

    @Override
    public BaseSessionInfo createNewSession() {
        String session_id = UUID.randomUUID().toString();
        BaseSessionInfo sessioninfo = new BaseSessionInfo(session_id);
        synchronized (sessionInfos) {
            if (sessionInfos == null) {
                sessionInfos = new ConcurrentHashMap<String, BaseSessionInfo>();
            }
        }
        sessionInfos.put(session_id, sessioninfo);
        return sessioninfo;
    }

    @Override
    public boolean updateSessionValue(String session_id, String session_key, String value) {
        BaseSessionInfo sessioninfo = sessionInfos.get(session_id);
        // sessin_id不存在
        if (sessioninfo == null) {
            return false;
        }
        // 更新session_key的值
        sessioninfo.getAttribute().put(session_key, value);
        return true;
    }

    @Override
    public String getSessionValue(String session_id, String session_key) {
        BaseSessionInfo sessioninfo = sessionInfos.get(session_id);
        // sessin_id不存在
        if (sessioninfo == null) {
            return null;
        }
        // 获取session_key的值
        return sessioninfo.getAttribute().get(session_key);
    }
}
