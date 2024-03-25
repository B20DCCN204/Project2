package com.javaweb.springbootnonjwt.utils;

public class ObjectUtil {
    public static boolean checkObject(Object obj){
        return (obj != null && !obj.toString().isEmpty());
    }

    public static boolean isNumber(Object obj){
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
