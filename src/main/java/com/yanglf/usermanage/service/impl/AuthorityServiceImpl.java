package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.domain.Authority;
import com.yanglf.usermanage.dao.AuthorityMapper;
import com.yanglf.usermanage.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
   private AuthorityMapper authorityMapper;
    @Override
    @Cacheable(cacheNames = "AuthoritygetAuthorityById")
    public Authority getAuthorityById(Long id) {
        return authorityMapper.selectByPrimaryKey(id);
    }
}
