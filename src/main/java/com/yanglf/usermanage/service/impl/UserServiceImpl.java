package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.dao.BlogUserMapper;
import com.yanglf.usermanage.dao.UserAuthorityMapper;
import com.yanglf.usermanage.domain.Authority;
import com.yanglf.usermanage.domain.BlogUser;
import com.yanglf.usermanage.service.UserService;
import com.yanglf.usermanage.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private BlogUserMapper blogUserMapper;
    @Autowired
    private UserAuthorityMapper userAuthorityMapper;
    @Override
    public void save(BlogUser user) {
        logger.info(user.toString());
        blogUserMapper.insert(user);
    }

    @Override
    public void delete(Integer id) {
        blogUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public BlogUser update(BlogUser user) {
        blogUserMapper.updateByPrimaryKey(user);
        return user;
    }

    @Override
    public BlogUser findById(Integer id) {
        return blogUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BlogUser> findAll() {
        return getList();
    }

    @Override
    public void login(BlogUser user) {
     BlogUser blogUser = blogUserMapper.selectByUserName(user.getUsername());
        if (blogUser!=null){
            String encode = MD5Utils.encode(user.getPassword() + blogUser.getSolt());
            if (blogUser.getPassword().equals(encode)){
                logger.info("登录成功");
            }else {
                logger.info("登录失败");
            }
        }else{
            logger.info("用户不存在");
        }
    }

    @Override
    public List<BlogUser> findBlogUserByUserNameLike(String username) {
        return  blogUserMapper.selectLikeUserName(username);

    }

    public List<BlogUser> getList(){
        List<BlogUser> list = new ArrayList<>();
        for (BlogUser user:blogUserMapper.selectAll()){
            list.add(user);
        }
        return list;



    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BlogUser blogUser = blogUserMapper.selectByUserName(username);
        List<Authority> authorities = userAuthorityMapper.selectByuserId(blogUser.getId());
        blogUser.setAuthorityList(authorities);
        return blogUser;
    }
}
