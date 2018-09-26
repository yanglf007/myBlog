package com.yanglf.usermanage.service;

import com.yanglf.usermanage.domain.Blog;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BlogService {
    List<Blog> findAll();
    void save(Blog blog);
    void remove(Long id);
    Blog findById(Long aLong);
    int updateByPrimaryKeySelective(Blog record);
    void readingIncrease(Long id);
}
