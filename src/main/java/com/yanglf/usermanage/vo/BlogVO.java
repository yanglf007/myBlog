package com.yanglf.usermanage.vo;

import com.yanglf.usermanage.demain.Blog;

public class BlogVO extends Blog{
    private String name;
    private String username;
    private String email;
    private String avator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public BlogVO(String name, String username, String email, String avator) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.avator = avator;
    }
}
