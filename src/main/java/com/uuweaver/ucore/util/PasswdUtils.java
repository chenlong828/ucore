package com.uuweaver.ucore.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class PasswdUtils {

	public static final int DEFAULT_PREFIX = 32;
	
	public static int prefix;

    // @TODO 先从配置文件取密码前缀，配置文件没有再设置为默认的。
	static {
		prefix = DEFAULT_PREFIX;
	}
	
	public static String generate(String source) {
        StringBuilder sb = new StringBuilder();
        // MD5摘要密码
        String md5 = DigestUtils.md5Hex(source);
        // 生成随机前缀
		String random = RandomStringUtils.randomAlphanumeric(prefix);
        // 前缀+MD5摘要生成SHA512散列
		String sha = DigestUtils.sha384Hex(random + md5);
		// 前缀+SHA散列作为密码
        sb.append(random).append(sha);
		return sb.toString();
	}

	public static boolean validate(String source, String passwd) {
        // MD5摘要密码
        String md5 = DigestUtils.md5Hex(source);
        // 获取随机前缀
        String random = passwd.substring(0, prefix);
        // 前缀+MD5摘要生成SHA512散列
		String sha = DigestUtils.sha384Hex(random + md5);

        return passwd.equals(random + sha);
    }

//    public static void main(String[] args){
//        String code = "程振东chenlong";
//        String passwd = generate(code);
//        System.out.println("code : " + code);
//        System.out.println("passwd : " + passwd);
//        System.out.println("passwd size : " + passwd.length());
//        System.out.println(validate(code, passwd));
//    }

}
