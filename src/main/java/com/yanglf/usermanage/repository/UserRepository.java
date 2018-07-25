package com.yanglf.usermanage.repository;

import com.yanglf.usermanage.demain.BlogUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<BlogUser,Integer> {

    BlogUser findBlogUserByUsername(String username);
    Page<BlogUser> findBlogUsersByUsernameLike(String username, Pageable pageable);

    @Override
    @CachePut
    <S extends BlogUser> S save(S entity);

    @Cacheable
    @Override
    Optional<BlogUser> findById(Integer integer);
}
