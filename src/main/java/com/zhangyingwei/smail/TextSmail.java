package com.zhangyingwei.smail;

import com.zhangyingwei.smail.config.SmailConfig;
import com.zhangyingwei.smail.exception.SmailException;
import com.zhangyingwei.smail.model.User;
import com.zhangyingwei.smail.utils.SmailAuth;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangyw on 2017/6/29.
 */
public class TextSmail implements ISmail{
    private SmailConfig config;
    private List<User> toUsers;
    private List<User> ccUsers;
    private List<User> bccUsers;

    public TextSmail(){
        this.config = new SmailConfig();
        this.toUsers = new ArrayList<User>();
        this.ccUsers = new ArrayList<User>();
        this.bccUsers = new ArrayList<User>();
    }
    public TextSmail(SmailConfig config) {
        this.config = config;
        this.toUsers = new ArrayList<User>();
        this.ccUsers = new ArrayList<User>();
        this.bccUsers = new ArrayList<User>();
    }

    public ISmail auth(String username,String password){
        this.config.setUsername(username);
        this.config.setPassword(password);
        return this;
    }

    public ISmail to(String username){
        this.toUsers.add(new User().setUsername(username));
        return this;
    }

    @Override
    public ISmail to(String username, String nikeName) {
        this.toUsers.add(new User().setUsername(username).setNikename(nikeName));
        return this;
    }

    public ISmail to(List<String> usernames) {
        this.toUsers.addAll(usernames.stream().map(username -> new User().setUsername(username)).distinct().collect(Collectors.toList()));
        return this;
    }

    public ISmail cc(String username) {
        this.ccUsers.add(new User().setUsername(username));
        return this;
    }

    @Override
    public ISmail cc(String username, String nikeName) {
        this.ccUsers.add(new User().setUsername(username).setNikename(nikeName));
        return this;
    }

    public ISmail cc(List<String> usernames) {
        this.ccUsers.addAll(usernames.stream().map(username -> new User().setUsername(username)).distinct().collect(Collectors.toList()));
        return this;
    }

    public ISmail bcc(String username) {
        this.bccUsers.add(new User().setUsername(username));
        return this;
    }

    @Override
    public ISmail bcc(String username, String nikeName) {
        this.bccUsers.add(new User().setUsername(username).setNikename(nikeName));
        return this;
    }

    public ISmail bcc(List<String> usernames) {
        this.bccUsers.addAll(usernames.stream().map(username -> new User().setUsername(username)).distinct().collect(Collectors.toList()));
        return this;
    }

    public void send(String subject,String content) throws UnsupportedEncodingException, MessagingException, SmailException {
        Session session = Session.getDefaultInstance(
                this.config.getConfProperties(),
                new SmailAuth(this.config.getFromUser())
        );
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(
                new InternetAddress(
                        this.config.getUsername(),
                        this.config.getNikename(),
                        this.config.getMailEncoding()
                )
        );

        this.addToUsers(message);
        this.addCcUsers(message);
        this.addBccUsers(message);

        message.setSubject(subject, this.config.getMailEncoding());
        message.setContent(content, this.config.getType());
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport = session.getTransport();
        transport.connect(this.config.getUsername(),this.config.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private void addBccUsers(MimeMessage message) throws UnsupportedEncodingException, MessagingException {
        for (int i = 0; i < this.bccUsers.size(); i++) {
            Address address = new InternetAddress(this.bccUsers.get(i).getUsername(), this.bccUsers.get(i).getNikename(), this.config.getMailEncoding());
            if(i == 0){
                message.setRecipient(
                        MimeMessage.RecipientType.BCC,
                        address
                );
            }else{
                message.addRecipient(
                        MimeMessage.RecipientType.BCC,
                        address
                );
            }
        }
    }

    private void addCcUsers(MimeMessage message) throws UnsupportedEncodingException, MessagingException, SmailException {
        for (int i = 0; i < this.ccUsers.size(); i++) {
            Address address = new InternetAddress(this.ccUsers.get(i).getUsername(), this.ccUsers.get(i).getNikename(), this.config.getMailEncoding());
            if(i == 0){
                message.setRecipient(
                        MimeMessage.RecipientType.CC,
                        address
                );
            }else{
                message.addRecipient(
                        MimeMessage.RecipientType.CC,
                        address
                );
            }
        }
    }

    private void addToUsers(MimeMessage message) throws SmailException, UnsupportedEncodingException, MessagingException {
        if(this.toUsers.size()<0){
            throw new SmailException("收件人列表为空");
        }
        for (int i = 0; i < this.toUsers.size(); i++) {
            Address address = new InternetAddress(this.toUsers.get(i).getUsername(), this.toUsers.get(i).getNikename(), this.config.getMailEncoding());
            if(i == 0){
                message.setRecipient(
                        MimeMessage.RecipientType.TO,
                        address
                );
            }else{
                message.addRecipient(
                        MimeMessage.RecipientType.TO,
                        address
                );
            }
        }
    }
}
