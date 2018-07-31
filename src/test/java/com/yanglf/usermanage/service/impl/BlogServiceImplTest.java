package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;
    @Test
    public void remove() {
        blogService.remove(61L);
    }
}