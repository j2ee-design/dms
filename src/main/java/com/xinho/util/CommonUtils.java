package com.xinho.util;

import java.util.regex.*;

public class CommonUtils {

    private static boolean match(String regex, String str) {
        // 没用过正则表达式，没想到它竟然没有判空！垃圾！
        if (str == null || str.trim().length()<=0){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isEmpty(String str){
        if (str == null || str.trim().length()==0){
            return true;
        }
        return false;
    }

    /**
     * 是否是数字(整)
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        return match("^[1-9]\\d*$",str);
    }
    public static boolean isNumber(Long num){
        return isNumber(num.toString());
    }
    public static boolean isData(String str){
        return match("^\\d{4}[-]\\d{2}[-]\\d{2}$",str);
    }

}
