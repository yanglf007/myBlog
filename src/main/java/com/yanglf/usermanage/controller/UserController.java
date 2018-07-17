package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.demain.BlogUser;
import com.yanglf.usermanage.service.UserService;
import com.yanglf.usermanage.utils.FTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {


    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Value("${avatarPath}")
    private String avatarPath;
    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title","注册页面");
        return "register";
    }




    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title","登录页面");
        return "login";
    }

    @GetMapping("/manage")
    public String manage(){
        return "userManage";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "async" ,required = false) boolean async,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "3") int pageSize,
                             @RequestParam(value = "username",required = false,defaultValue = "") String username,
                             Model model){

        logger.info("username: "+username+" pageIndex:"+pageIndex+" pageSize:"+pageSize);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<BlogUser> page = userService.findBlogUserByUserNameLike(username, pageable);
        List<BlogUser> content = page.getContent();
        logger.info("page:",page.toString());
        model.addAttribute("page",page);
        model.addAttribute("userList",content);
        return new ModelAndView(async==true?"users/list :: #mainContainerReplace":"users/list","userModel",model);

    }

    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable String username,Model model){
        BlogUser user = (BlogUser) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("userspace/profile","userModel",model);
    }

    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String modify(@PathVariable String username,BlogUser user){
        BlogUser blogUser = userService.findById(user.getId());
        blogUser.setEmail(user.getEmail());
        blogUser.setName(user.getName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());
        boolean matches = encoder.matches(blogUser.getPassword(), encodePassword);

        if (!matches){
            blogUser.setEncodePassword(user.getPassword());
        }
        userService.save(user);
        return "redirect:/user/"+username+"/profile";
    }

    /**
     * 获取头像页面
     */

    @GetMapping("{username}/avator")
    public ModelAndView getAvator(@PathVariable String username,Model model){
        logger.debug("获取头像");
        return new ModelAndView("userspace/avator");
    }

    @PostMapping("{username}/avator")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView saveAvator(@PathVariable String username,Model model,
                                   @RequestBody BlogUser user){
        logger.debug("获取头像");
        BlogUser blogUser = userService.findById(user.getId());
        blogUser.setAvator(user.getAvator());
        userService.save(blogUser);
        return new ModelAndView("userspace/avator");

    }

    @PostMapping("{username}/avator/upload")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile uploadFile, @PathVariable String username, HttpServletRequest request) throws IOException {

        InputStream inputStream = uploadFile.getInputStream();
        String filename = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
        FTPUtils.upload(inputStream, filename,avatarPath);
        return ResponseEntity.status(HttpStatus.OK).body("http://132.232.14.175/avatar/"+filename);
    }
}
