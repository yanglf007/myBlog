package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.demain.Blog;
import com.yanglf.usermanage.demain.BlogUser;
import com.yanglf.usermanage.repository.BlogRepository;
import com.yanglf.usermanage.service.BlogService;
import com.yanglf.usermanage.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;


    @GetMapping("/blogs/{username}/editor")
    public ModelAndView createBolg(@PathVariable String username, Model model){
        model.addAttribute("blog",new Blog());
        return new ModelAndView("userspace/edit","blogModel",model);
    }

    /**
     * 发布博客
     * @param username
     * @param blog
     * @return
     */
    @PostMapping("/blogs/{username}/editor")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<ResponseMessage> save(@PathVariable String username, @RequestBody Blog blog){
        BlogUser user = (BlogUser) userDetailsService.loadUserByUsername(username);
        blog.setUser(user);
        blogService.save(blog);
        String redirectUrl = "/blogs/"+username+"/"+blog.getId();
        return ResponseEntity.ok().body(new ResponseMessage(true,"处理成功",redirectUrl));
    }

    @GetMapping("/blogs/{username}/editor/{id}")
    public ModelAndView getArtical(@PathVariable("username") String username,
                                   @PathVariable("id") Long id,Model model){
        Blog blog = blogRepository.findById(id).get();
        model.addAttribute("blog",blog);
        return new ModelAndView("userspace/edit","blogModel",model);
    }



    /**
     * 获取博客展示界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{username}/{id}")
    public String getBlogById(@PathVariable("username") String username,@PathVariable("id") Long id, Model model) {
        // 每次读取，简单的可以认为阅读量增加1次
       // blogService.readingIncrease(id);

        boolean isBlogOwner = false;

        // 判断操作用户是否是博客的所有者
        if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            BlogUser principal = (BlogUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal !=null && username.equals(principal.getUsername())) {
                isBlogOwner = true;
            }
        }

        model.addAttribute("isBlogOwner", isBlogOwner);
        model.addAttribute("blogModel",blogRepository.findById(id).get());

        return "userspace/blog";
    }

    /**
     * 删除博客
     */
    @GetMapping("/blogs/{id}")
    public ResponseEntity<ResponseMessage>  delete(@PathVariable Long id){
        blogService.remove(id);
        return ResponseEntity.ok().body(new ResponseMessage(true,"删除成功！","/index"));
    }

}
