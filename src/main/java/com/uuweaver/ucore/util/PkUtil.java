package com.uuweaver.ucore.util;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-6
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
public class PkUtil {

    private static final char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9'};

    private static final int CHAR_COUNT = chars.length;
    private static final Random rd = new Random();
    private static final int TOKEN_LENGTH = 13;

    public static String genRandomStr() {
        StringBuffer buf = new StringBuffer(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            buf.append(chars[rd.nextInt(CHAR_COUNT)]);
        }
        return buf.toString();
    }

    public static String getUniChar() {
        String uniChar = DateUtils.getCurrentTime().replaceAll("-", "").replaceAll(":", "").replace(".", "").replace(" ", "");
        uniChar = uniChar + genRandomStr();
        return uniChar;
    }
}
