package com.zhangyingwei.smail;


import com.zhangyingwei.smail.config.SmailConfig;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhangyw on 2017/6/28.
 */

public class SmailTest {
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        new Smail(new SmailConfig().setStarttls(true))
                .auth("1170404227@qq.com", "723129")
                .to("zhangyw_001@126.com")
                .send("简历", "你好，我是某某介绍过来的", null);
    }
}