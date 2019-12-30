package com.restaurant.config;

import org.springframework.util.DigestUtils;

import java.util.UUID;

public class Utils {
    //生成uuid用于重命名
    public static String uuidStr(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String reName(String fileName){
        for (int i = fileName.length();i>1;i--){
            if (fileName.charAt(i-1)=='.'){
                fileName = fileName.substring(i-1,fileName.length());
                fileName=uuidStr()+fileName;
            }
        }
        System.out.println(fileName);
        return fileName;
    }


    /**
     * 生成md5
     * @param str
     * @return
     */
    //盐，用于混交md5
    private static final String slat = "adoivjnsdkla";
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
