package com.zhangyingwei.smail.model;

/**
 * Created by zhangyw on 2017/6/28.
 */
public class User {
    private String username;
    private String password;
    private String nikename;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikename() {
        this.nikename = this.nikename == null ? this.username : this.nikename;
        return nikename;
    }

    public User setNikename(String nikename) {
        this.nikename = nikename;
        return this;
    }
}
