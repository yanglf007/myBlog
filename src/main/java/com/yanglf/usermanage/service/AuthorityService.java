package com.yanglf.usermanage.service;

import com.yanglf.usermanage.demain.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {
    Authority getAuthorityById(Long id);
}
