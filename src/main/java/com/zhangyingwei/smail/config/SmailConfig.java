package com.zhangyingwei.smail.config;

import com.zhangyingwei.smail.model.User;
import com.zhangyingwei.smail.utils.SmailUtils;

import java.util.Properties;

/**
 * Created by zhangyw on 2017/6/28.
 */
public class SmailConfig {
    private String mailEncoding = SmailEncoding.UTF8;
    private String type;
    private User fromUser;
    private String receiveSever;
    private Integer receivePort;
    private String sendServer;
    private Integer sendPort;
    private boolean starttls = false;
    /**
     * 使用代理
     * 0-否
     * 1-是
     *
     */
    private Integer userProxy = 0; //TODO

    public SmailConfig() {
        this.fromUser = new User();
    }

    public boolean getStarttls() {
        return starttls;
    }

    public SmailConfig setStarttls(Boolean starttls) {
        this.starttls = starttls;
        return this;
    }

    public String getMailEncoding() {
        return mailEncoding;
    }

    public SmailConfig setMailEncoding(String mailEncoding) {
        this.mailEncoding = mailEncoding;
        return this;
    }

    public String getUsername() {
        return this.fromUser.getUsername();
    }

    public String getType() {
        return "text/html;charset="+mailEncoding;
    }

    public SmailConfig setUsername(String username) {
        this.fromUser.setUsername(username);
        return this;
    }

    public String getPassword() {
        return this.fromUser.getPassword();
    }

    public SmailConfig setPassword(String password) {
        this.fromUser.setPassword(password);
        return this;
    }

    public String getNikename() {
        return this.fromUser.getNikename();
    }

    public SmailConfig setNikename(String nikename) {
        this.fromUser.setNikename(nikename);
        return this;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public String getReceiveSever() {
        return receiveSever;
    }

    public SmailConfig setReceiveSever(String receiveSever) {
        this.receiveSever = receiveSever;
        return this;
    }

    public Integer getReceivePort() {
        return receivePort;
    }

    public SmailConfig setReceivePort(Integer receivePort) {
        this.receivePort = receivePort;
        return this;
    }

    public String getSendServer() {
        this.sendServer = SmailUtils.getSendServer(this.fromUser.getUsername());
        return sendServer;
    }

    public SmailConfig setSendServer(String sendServer) {
        this.sendServer = sendServer;
        return this;
    }

    public Integer getSendPort() {
        this.sendPort = SmailUtils.getSendPort(this.getSendServer(),this.starttls);
        return sendPort;
    }

    public SmailConfig setSendPort(Integer sendPort) {
        this.sendPort = sendPort;
        return this;
    }

    public Integer getUserProxy() {
        return userProxy;
    }

    public SmailConfig setUserProxy(Integer userProxy) {
        this.userProxy = userProxy;
        return this;
    }

    public Properties getConfProperties(){
        Properties config = new Properties();
        config.setProperty("mail.transport.protocol", "smtp");
        config.put("mail.smtp.host", this.getSendServer());
        config.put("mail.smtp.port", this.getSendPort());
        config.put("mail.smtp.starttls.enable", this.starttls);
        if(this.starttls){
            config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            config.setProperty("mail.smtp.socketFactory.fallback", "false");
        }
        config.put("mail.smtp.auth", true);
        System.out.println("config: "+config);
        return config;
    }
}
