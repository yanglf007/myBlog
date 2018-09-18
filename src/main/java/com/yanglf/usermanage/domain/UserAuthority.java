package com.yanglf.usermanage.domain;

public class UserAuthority {
    private Integer userId;

    private Long authorityId;

    public UserAuthority(Integer userId, Long authorityId) {
        this.userId = userId;
        this.authorityId = authorityId;
    }

    public UserAuthority() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}