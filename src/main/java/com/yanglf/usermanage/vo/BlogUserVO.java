package com.yanglf.usermanage.vo;

import com.yanglf.usermanage.domain.Blog;
import com.yanglf.usermanage.domain.BlogUser;

public class BlogUserVO extends Blog {
   private BlogUser blogUser;

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }
}
