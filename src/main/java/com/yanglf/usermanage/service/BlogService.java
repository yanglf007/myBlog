package com.yanglf.usermanage.service;

import com.yanglf.usermanage.demain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    void save(Blog blog);
    void remove(Long id);
    Optional<Blog> findById(Long aLong);
}
