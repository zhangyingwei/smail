package com.zhangyingwei.smail;

import com.zhangyingwei.smail.config.SmailConfig;
import com.zhangyingwei.smail.model.User;
import com.zhangyingwei.smail.utils.SmailAuth;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyw on 2017/6/28.
 */
public class Smail {
    private SmailConfig config;
    private User toUser;

    public Smail(){
        this.config = new SmailConfig();
    }
    public Smail(SmailConfig config) {
        this.config = config;
    }

    public Smail auth(String username,String password){
        this.config.setUsername(username);
        this.config.setPassword(password);
        return this;
    }

    public Smail to(String username){
        this.toUser = new User();
        toUser.setUsername(username);
        return this;
    }

    public Smail send(String title,String content) {

        return this;
    }

    public void send(String subject, String content, List<String> files) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(
                this.config.getConfProperties(),
                new SmailAuth(this.config.getFromUser())
        );
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(
                new InternetAddress(
                        this.config.getUsername(),
                        "USER_AA",
                        this.config.getMailEncoding()
                )
        );
        message.setRecipient(
                MimeMessage.RecipientType.TO,
                new InternetAddress(this.toUser.getUsername(), "USER_CC", this.config.getMailEncoding())
        );

        message.setSubject("邮件主题-简历", this.config.getMailEncoding());
        message.setContent("TEST这是邮件正文。。。", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport();
        transport.connect(this.config.getUsername(),this.config.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("end");
    }
}
