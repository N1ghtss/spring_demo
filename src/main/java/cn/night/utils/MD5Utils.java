package cn.night.utils;

import org.springframework.util.DigestUtils;


public class MD5Utils {

    //盐
    private static final String salt = "StudentSystemManager###$$@@";

    public static String getMD5(String string) {
        String val = string + salt;
        return DigestUtils.md5DigestAsHex(val.getBytes());
    }

    public static void main(String[] args) throws Exception {
////        System.out.println(getMD5("123456"));
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.format(date);
//        System.out.println(date);

    }

}
