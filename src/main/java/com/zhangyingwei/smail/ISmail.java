package com.zhangyingwei.smail;

import com.zhangyingwei.smail.config.SmailConfig;
import com.zhangyingwei.smail.exception.SmailException;
import com.zhangyingwei.smail.model.User;
import com.zhangyingwei.smail.utils.SmailAuth;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyw on 2017/6/28.
 */
public interface ISmail {

    public ISmail auth(String username, String password);

    public ISmail to(String username);

    public ISmail to(String username,String nikeName);

    public ISmail to(List<String> usernames);

    public ISmail cc(String username);

    public ISmail cc(String username,String nikeName);

    public ISmail cc(List<String> username);

    public ISmail bcc(String username);

    public ISmail bcc(String username,String nikeName);

    public ISmail bcc(List<String> username);

    public void send(String subject, String content) throws UnsupportedEncodingException, MessagingException, SmailException;
}
