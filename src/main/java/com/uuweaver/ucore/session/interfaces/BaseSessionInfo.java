package com.uuweaver.ucore.session.interfaces;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：保存基本Session的信息类
 * Author: ChenLong
 * Date: 12-9-10
 * Time: 上午9:39
 */
public class BaseSessionInfo {
    private String sessionID;
    private Date expireDate;
    private String sessionTag;
    private Map<String, String> attribute;

    public BaseSessionInfo(String sessionID) {
        this.sessionID = sessionID;
        attribute = new HashMap<String, String>();
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionTag() {
        return sessionTag;
    }

    public void setSessionTag(String sessionTag) {
        this.sessionTag = sessionTag;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }
}
