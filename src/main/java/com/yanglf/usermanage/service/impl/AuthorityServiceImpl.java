package com.yanglf.usermanage.service.impl;

import com.yanglf.usermanage.demain.Authority;
import com.yanglf.usermanage.repository.AuthorityRepository;
import com.yanglf.usermanage.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
   private AuthorityRepository authorityRepository;
    @Override
    @Cacheable(cacheNames = "AuthoritygetAuthorityById")
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).get();
    }
}
