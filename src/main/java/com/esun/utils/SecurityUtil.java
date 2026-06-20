package com.esun.utils;

public class SecurityUtil {
    public static boolean isValidInput(String input){
        if(input==null||input.trim().isEmpty()){
            return false;
        }
        return input.matches("^[a-zA-Z0-9\\u4e00-\\u9fa5]+$");
    }
    public static String sanitize(String input){
        if(input==null){
            return  null;

    }
    return input.replaceAll("<[^>]*>","");

    }

}
