package com.yanglf.usermanage.domain;

import java.util.Date;

public class Blog {
    private Long id;

    private Long comments;

    private Date createTime;

    private Long likes;

    private Long reading;

    private String summary;

    private String title;

    private Integer userId;

    private String content;
    private String htmlContent;

    public BlogUser getUser() {
        return user;
    }

    public void setUser(BlogUser user) {
        this.user = user;
    }

    private BlogUser user;
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }


    public Blog(Long id, Long comments, Date createTime,
                Long likes, Long reading, String summary, String title, Integer userId,String content, String htmlContent) {
        this.id = id;
        this.comments = comments;
        this.createTime = createTime;
        this.likes = likes;
        this.reading = reading;
        this.summary = summary;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.htmlContent = htmlContent;
    }


    public Blog() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getReading() {
        return reading;
    }

    public void setReading(Long reading) {
        this.reading = reading;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}