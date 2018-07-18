package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.demain.Blog;
import com.yanglf.usermanage.repository.BlogRepository;
import com.yanglf.usermanage.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;


    @Override
    @Cacheable(cacheNames = "PagefindAll")
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}
