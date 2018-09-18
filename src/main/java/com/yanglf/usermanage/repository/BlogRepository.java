package com.yanglf.usermanage.repository;

import com.yanglf.usermanage.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BlogRepository extends JpaRepository<Blog,Long>{

    Page<Blog> findAll(Pageable pageable);


    @Override
    <S extends Blog> S save(S entity);

    @Override
    Optional<Blog> findById(Long aLong);
}
