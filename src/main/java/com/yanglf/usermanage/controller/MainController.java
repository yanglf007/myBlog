package com.yanglf.usermanage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanglf.usermanage.domain.Authority;
import com.yanglf.usermanage.domain.Blog;
import com.yanglf.usermanage.domain.BlogUser;
import com.yanglf.usermanage.service.AuthorityService;
import com.yanglf.usermanage.service.BlogService;
import com.yanglf.usermanage.service.UserService;
import com.yanglf.usermanage.utils.AccountCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);
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
    public String firstpage(){
        return "redirect:/index/0";
    }

    @GetMapping("/index/{pageIndex}")
    public ModelAndView index( @PathVariable(value = "pageIndex",required = false) int pageIndex,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "4") int pageSize,Model model){

        PageHelper.startPage(pageIndex,pageSize,"id desc");
        List<Blog> list = blogService.findAll();
        PageInfo page = new PageInfo(list);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);

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

    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/register" )
    public String register(BlogUser user, Model model){
        List<Authority>authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(2L));
        user.setAuthorityList(authorities);
        //注册之前判断账号的有效性
        AccountCheckUtil.check(user);
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
