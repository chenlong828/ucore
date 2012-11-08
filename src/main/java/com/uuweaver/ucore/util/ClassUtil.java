package com.uuweaver.ucore.util;

/**
 * Description： 根据Class的完整路径，返回Class在Spring中自动注册的name（不是很管用，建议放弃)
 * Author: ChenLong
 * Date: 12-10-12
 * Time: 下午7:06
 */
public class ClassUtil {
    public static String getSpringNameFromClass(String class_name)
    {
        String[] tokens = class_name.split("\\.");
        int count = tokens.length;
        String spring_name = tokens[count - 1];
        char first_letter = spring_name.charAt(0);
        String first = new String(new char[] {first_letter});
        spring_name = spring_name.replaceFirst(first, first.toLowerCase());
        return spring_name;
    }
}
