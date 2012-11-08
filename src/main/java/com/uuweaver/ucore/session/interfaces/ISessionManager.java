package com.uuweaver.ucore.session.interfaces;

/**
 * Description：实现Session的自动化管理内部功能接口
 * Author: ChenLong
 * Date: 12-9-10
 * Time: 上午9:38
 */
public interface ISessionManager {
    BaseSessionInfo createNewSession();
    boolean updateSessionValue(String session_id, String session_key, String value);
    String getSessionValue(String session_id, String session_key);
}
