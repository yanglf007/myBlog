package com.yanglf.usermanage.service;

import com.yanglf.usermanage.domain.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {
    Authority getAuthorityById(Long id);
}
