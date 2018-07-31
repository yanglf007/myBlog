package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.demain.Blog;
import com.yanglf.usermanage.repository.BlogRepository;
import com.yanglf.usermanage.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


@Service

public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;


    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    @CachePut(value = "blogCache",key = "#blog.id")
    public void save(Blog blog) {
        if (blog.getUser()==null){
            throw new RuntimeException("用户不能为空！");
        }
        if (blog.getId()!=-1){
            Optional<Blog> optional = blogRepository.findById(blog.getId());
            Blog nblog = optional.get();
            BeanUtils.copyProperties(blog, nblog);
            nblog.setCreateTime(new Timestamp(new Date().getTime()));
            blogRepository.save(nblog);
        }else {
            blog.setId(null);
            blogRepository.save(blog);
        }
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "blogCache",key = "#aLong")
    public Optional<Blog> findById(Long aLong) {
        return blogRepository.findById(aLong);
    }


}
