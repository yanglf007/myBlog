package com.yanglf.usermanage.controller;

import com.yanglf.usermanage.domain.Blog;
import com.yanglf.usermanage.esclient.BlogClient;
import com.yanglf.usermanage.esclient.ProductClient;
import com.yanglf.usermanage.service.BlogService;
import com.yanglf.usermanage.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2018/10/27 22:30
 * @Description:
 */
@RestController
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogClient blogClient;
    @GetMapping("/getMsg")
    public String getMsg() {
        String msg = productClient.msg();
        System.out.println(msg);
        return msg;
    }

    @GetMapping("/saveBlog")
    public ResponseEntity save(){

        List<Blog> all = blogService.findAll();
        all.forEach(blog -> {
            BlogVO blogVO = new BlogVO();
            BeanUtils.copyProperties(blog, blogVO);
            ResponseEntity responseEntity = blogClient.save(blogVO);
        });

        return new ResponseEntity(HttpStatus.OK);
    }


}
