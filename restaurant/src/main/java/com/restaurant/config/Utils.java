package com.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Utils {
    @Autowired
    StringRedisTemplate redis;
    //生成uuid用于重命名
    public static String uuidStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String reName(String fileName) {
        for (int i = fileName.length(); i > 1; i--) {
            if (fileName.charAt(i - 1) == '.') {
                fileName = fileName.substring(i - 1, fileName.length());
                fileName = uuidStr() + fileName;
            }
        }
        System.out.println(fileName);
        return fileName;
    }


    /**
     * 生成md5
     *
     * @param str
     * @return
     */
    //盐，用于混交md5
    private static final String slat = "adoivjnsdkla";

    public static String getMD5(String str) {
        String base = str + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 生成订单码
     *
     * @param
     * @return
     */
    public static String getOrderCode() {
        String  now=  LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String orderNum = new StringRedisTemplate().opsForValue().increment("orderNum").toString();
        System.out.println(now+orderNum);
        return now+orderNum;
//        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
//        return md5;
    }

}
