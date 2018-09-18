package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.dao.BlogMapper;
import com.yanglf.usermanage.domain.Blog;
import com.yanglf.usermanage.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public List<Blog> findAll() {
        return blogMapper.selectAll();
    }

    @Override
    public void save(Blog blog) {
        if (blog.getUserId()==null){
            throw new RuntimeException("用户不能为空！");
        }
        blogMapper.insert(blog);
    }




    @Override
    public void remove(Long id) {

        blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Blog findById(Long aLong) {

        return blogMapper.selectByPrimaryKey(aLong);
    }


}
