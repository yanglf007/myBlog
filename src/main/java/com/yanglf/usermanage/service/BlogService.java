package com.yanglf.usermanage.service;

import com.yanglf.usermanage.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface BlogService {
    Page<Blog> findAll(Pageable pageable);
    void save(Blog blog);
    void remove(Long id);
    Optional<Blog> findById(Long aLong);
}
