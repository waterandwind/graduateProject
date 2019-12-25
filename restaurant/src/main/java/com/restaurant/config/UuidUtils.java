package com.restaurant.config;

import java.util.UUID;

public class UuidUtils {
    public static String uuidStr(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String reName(String fileName){
        for (int i = fileName.length();i>1;i--){
            if (fileName.charAt(i-1)=='.'){
                fileName.substring(i-1,fileName.length());
                fileName=uuidStr()+fileName;
            }
        }
        return fileName;
    }

}
