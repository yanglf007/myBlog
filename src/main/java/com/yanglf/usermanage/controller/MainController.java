package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.demain.Authority;
import com.yanglf.usermanage.demain.Blog;
import com.yanglf.usermanage.demain.BlogUser;
import com.yanglf.usermanage.service.AuthorityService;
import com.yanglf.usermanage.service.BlogService;
import com.yanglf.usermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @Autowired
    private AuthorityService authorityService;
    @GetMapping("/")
    public String root(){
        return "redirect:index";
    }

    @GetMapping("/index")
    public ModelAndView index( @RequestParam(value = "pageIndex",required = false,defaultValue = "0") int pageIndex,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "5") int pageSize,Model model){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"createTime"));

        Pageable pageable = new PageRequest(pageIndex, pageSize,sort);

        Page<Blog> page = blogService.findAll(pageable);
        List<Blog> blogList = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("blogList", blogList);

        return new ModelAndView("index","blogModel",model);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        model.addAttribute("errorMsg","用户名或密码错误!");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 注册用户
     */


    @PostMapping(value = "/register")
    public String register(BlogUser user, Model model){
        List<Authority>authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(2L));
        user.setAuthorities(authorities);
        user.setEncodePassword(user.getPassword());
        userService.save(user);
        model.addAttribute("title","登录页面");
        return "redirect:/login";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping("/login")
    public String login(BlogUser user, Model model){
        userService.login(user);
        model.addAttribute("username",user.getUsername());
        model.addAttribute("result","登录成功");
        return "firstPage";
    }
}
