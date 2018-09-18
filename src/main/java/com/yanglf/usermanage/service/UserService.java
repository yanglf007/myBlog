package com.yanglf.usermanage.service;

import com.yanglf.usermanage.domain.BlogUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<BlogUser> findBlogUserByUserNameLike(String username, Pageable pageable);


}
