package com.uuweaver.ucore.util;

import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 12-9-28
 * Time: 下午6:23
 * To change this template use File | Settings | File Templates.
 */
public final class DefaultPolicyResource {

    private static ResourceBundle defaultPolicy = ResourceBundle.getBundle("default_policy");

    public static String getPolicy(String key){
        return defaultPolicy.getString(key);
    }
}
