package com.uuweaver.ucore.util;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * UUIDUtils 用于生成32位的GUID
 * User: Beckham007
 * Date: 13-3-26
 * Time: 上午9:27
 */
public class UUIDUtils {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return StringUtils.replace(uuid.toString().trim(), "-", "");
    }

}
