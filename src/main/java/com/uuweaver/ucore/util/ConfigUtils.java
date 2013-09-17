package com.uuweaver.ucore.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

public final class ConfigUtils {

    private static Configuration config = null;

    static {
        try {
            DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder("app.config.xml");
            config = configurationBuilder.getConfiguration();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getIntValue(final String key) {
        int reInt = 0;
        try {
            reInt = config.getInt(key);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return reInt;
    }

    public static long getLongValue(final String key) {
        long reLong = 0;
        try {
            reLong = config.getLong(key);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }

        return reLong;
    }

    public static double getDoubleValue(final String key) {
        double reDouble = 0;
        try {
            reDouble = config.getDouble(key);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }

        return reDouble;
    }

    public static String getStringValue(String key) {
        String str = "";
        try {
            str = config.getString(key);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return str;
    }

    public static boolean getBooleanValue(String key) {
        boolean flag = false;
        try {
            flag = config.getBoolean(key);
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }

        return flag;
    }

}
