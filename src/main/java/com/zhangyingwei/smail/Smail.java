package com.zhangyingwei.smail;

import com.zhangyingwei.smail.config.SmailConfig;
import com.zhangyingwei.smail.exception.SmailException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zhangyw on 2017/6/28.
 */
public class Smail implements ISmail{

    private ISmail smail;

    public Smail() {
        this.smail = new TextSmail();
    }
    public Smail(SmailConfig config){
        this.smail = new TextSmail(config);
    }

    public ISmail auth(String username, String password) {
        return this.smail.auth(username, password);
    }

    public ISmail to(String username) {
        return this.smail.to(username);
    }

    @Override
    public ISmail to(String username, String nikeName) {
        return this.smail.to(username, nikeName);
    }

    public ISmail to(List<String> usernames) {
        return this.smail.to(usernames);
    }

    public ISmail cc(String username) {
        return this.smail.cc(username);
    }

    @Override
    public ISmail cc(String username, String nikeName) {
        return this.smail.cc(username, nikeName);
    }

    public ISmail cc(List<String> username) {
        return this.smail.cc(username);
    }

    public ISmail bcc(String username) {
        return this.smail.bcc(username);
    }

    @Override
    public ISmail bcc(String username, String nikeName) {
        return this.smail.bcc(username, nikeName);
    }

    public ISmail bcc(List<String> username) {
        return this.smail.bcc(username);
    }

    public void send(String title, String content) throws SmailException, UnsupportedEncodingException, MessagingException {
        this.smail.send(title,content);
    }
}
