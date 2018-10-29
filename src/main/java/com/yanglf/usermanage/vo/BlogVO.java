package com.yanglf.usermanage.vo;

import java.util.Date;

/**
 * @author yanglf
 */
public class BlogVO{
    private Long id;

    private Long comments;

    private Date createTime;

    private Long likes;

    private Long reading;

    private String summary;

    private String title;

    private Integer userId;

    private String content;

    @Override
    public String toString() {
        return "BlogVO{" +
                "id=" + id +
                ", comments=" + comments +
                ", createTime=" + createTime +
                ", likes=" + likes +
                ", reading=" + reading +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                '}';
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
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
