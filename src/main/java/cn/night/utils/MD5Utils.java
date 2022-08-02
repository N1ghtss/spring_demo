package cn.night.utils;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    // 盐值
    private static final String salt = "StudentSystemManager###123456";

    /*
     * 对密码进行加密
     * @param str 要加密的字符串
     * @return
     * */
    public static String getMD5(String str) {
        String value = str + salt;
        // md5 hash 值为16位的hex值
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(getMD5("123456"));
    }


}
