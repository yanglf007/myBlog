package com.yanglf.usermanage.domain;

import java.util.Date;

public class BlogWithBLOBs extends Blog {
    private String content;

    private String htmlContent;

    public BlogWithBLOBs(Long id, Long comments, Date createTime, Long likes, Long reading, String summary, String title, Integer userId, String content, String htmlContent) {
        super(id, comments, createTime, likes, reading, summary, title, userId);
        this.content = content;
        this.htmlContent = htmlContent;
    }

    public BlogWithBLOBs() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }
}