package com.yanglf.usermanage.domain;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private Long id;

    private String name;

    public Authority(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Authority() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String getAuthority() {
        return name;
    }
}