package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.demain.BlogUser;
import com.yanglf.usermanage.repository.UserRepository;
import com.yanglf.usermanage.service.UserService;
import com.yanglf.usermanage.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private UserRepository userRepository;
    @Override
    public void save(BlogUser user) {
        logger.info(user.toString());
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public BlogUser update(BlogUser user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public BlogUser findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<BlogUser> findAll() {
        return getList();
    }

    @Override
    public void login(BlogUser user) {
     BlogUser blogUser = userRepository.findBlogUserByUsername(user.getUsername());
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
    public Page<BlogUser> findBlogUserByUserNameLike(String username, Pageable pageable) {
        username = username==null?"%%":"%"+username+"%";
       return userRepository.findBlogUsersByUsernameLike(username,pageable);

    }

    public List<BlogUser> getList(){
        List<BlogUser> list = new ArrayList<>();
        for (BlogUser user:userRepository.findAll()){
            list.add(user);
        }
        return list;



    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findBlogUserByUsername(username);
    }
}
