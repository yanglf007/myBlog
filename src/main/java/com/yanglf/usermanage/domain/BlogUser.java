package com.yanglf.usermanage.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlogUser implements UserDetails {
    private Integer id;
    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2,max = 20)
    private String name;

    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50)
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotEmpty(message = "账号不能为空")
    @Size(min=3, max=20)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max=100)
    private String password;

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    private Timestamp updateTime;

    private String solt;
    private Timestamp createTime;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getSolt() {
        return solt;
    }

    public void setSolt(String solt) {
        this.solt = solt;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    private List<Authority> authorityList;

    private String avator;

    private  String status;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getUsername() {
        return username;
    }

    public void setEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        this.password = encodePasswd;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
    /*    for (GrantedAuthority authority : this.authorityList){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }*/
        return simpleAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityList=" + authorityList +
                ", avatar='" + avator + '\'' +
                '}';
    }

    public BlogUser(Integer id, String avator,
                    Timestamp createTime, String email, String name,
                    String password, String solt, String status, Timestamp updateTime,
                    String username) {
        this.id = id;
        this.avator = avator;
        this.createTime = createTime;
        this.email = email;
        this.name = name;
        this.password = password;
        this.solt = solt;
        this.status = status;
        this.updateTime = updateTime;
        this.username = username;
    }

    public BlogUser() {
    }
}

