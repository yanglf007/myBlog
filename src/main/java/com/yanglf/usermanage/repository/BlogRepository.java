package com.yanglf.usermanage.repository;

import com.yanglf.usermanage.demain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long>{

    Page<Blog> findAll(Pageable pageable);
 //   @Query(value = "select new com.yanglf.usermanage.vo.BlogVO(b,u) from Blog b ,BlogUser u where b.user.i")
   // Page<Blog> findBlogPageable(Pageable pageable);
}
