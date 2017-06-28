package com.zhangyingwei.smail.utils;


/**
 * Created by zhangyw on 2017/6/28.
 */
public class SmailUtils {

    public static String getSendServer(String username) {
        return getSmtpServer(username);
    }

    private static String getSmtpServer(String username) {
        return "smtp."+username.split("@")[1];
    }

    public static Integer getSendPort(String server, boolean starttls) {
        Boolean tls = Boolean.valueOf(starttls);
        if(tls){
            return 465;
        }
        return 25;
    }
}
