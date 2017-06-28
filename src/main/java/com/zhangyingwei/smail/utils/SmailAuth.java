package com.zhangyingwei.smail.utils;

import com.zhangyingwei.smail.model.User;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by zhangyw on 2017/6/28.
 */
public class SmailAuth extends Authenticator {
    private User fromUser;

    public SmailAuth(User fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.fromUser.getUsername(), this.fromUser.getPassword());
    }
}
