package com.yanglf.usermanage.service;

import com.yanglf.usermanage.domain.BlogUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    void save(BlogUser user);
    void delete(Integer id);
    BlogUser update(BlogUser user);
    BlogUser findById(Integer id);
    List<BlogUser> findAll();
    void login(BlogUser user);
    List<BlogUser> findBlogUserByUserNameLike(String username);


}
