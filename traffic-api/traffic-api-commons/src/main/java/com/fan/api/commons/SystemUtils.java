package com.fan.api.commons;

/**
 * @date: 2022/11/20 - 11 - 20 - 14:39
 * @version: 1.0
 * 系统通用的工具类
 */
public class SystemUtils {

    /**
     * 判断是否为空对象
     * @param object
     * @return TRUE 空   FALSE 非空
     */
    public static boolean isNull(Object object){
        return null == object;
    }

    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return true：为空  false：非空
     */
    public static boolean isNullOrEmpty(String str){
        return null == str || str.trim().equals("");
    }

}
