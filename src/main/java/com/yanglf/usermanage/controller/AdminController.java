package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 获取菜单列表
     */

    @RequestMapping(value = {"/index","/"})
    public ModelAndView menu(Model model){
        List list = new ArrayList();
        list.add(new Menu("用户管理","/user/list"));
        list.add(new Menu("角色管理","/roles"));
        list.add(new Menu("博客管理","/blogs"));
        list.add(new Menu("评论管理","/commits"));
        model.addAttribute("list",list);
        return new ModelAndView("/admins/index","model",model);
    }
}
