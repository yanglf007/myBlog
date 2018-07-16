package com.yanglf.usermanage.repository;

import com.yanglf.usermanage.demain.BlogUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BlogUser,Integer> {

    BlogUser findBlogUserByUsername(String username);
    Page<BlogUser> findBlogUsersByUsernameLike(String username, Pageable pageable);
}
