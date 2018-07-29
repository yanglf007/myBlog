package com.yanglf.usermanage.repository;

import com.yanglf.usermanage.demain.Blog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "blogs")
public interface BlogRepository extends JpaRepository<Blog,Long>{

    Page<Blog> findAll(Pageable pageable);


    @Override
    <S extends Blog> S save(S entity);

    @Override
    Optional<Blog> findById(Long aLong);
}
