package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.dao.BlogMapper;
import com.yanglf.usermanage.domain.Blog;
import com.yanglf.usermanage.service.BlogService;
import com.yanglf.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserService userService;

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

        Blog blog = blogMapper.selectByPrimaryKey(aLong);
        Integer userId = blog.getUserId();
        blog.setUser(userService.findById(userId));
        return blog;
    }
    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void readingIncrease(Long id) {
        blogMapper.readingIncrease(id);
    }
}
