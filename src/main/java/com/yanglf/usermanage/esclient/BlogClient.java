package com.yanglf.usermanage.esclient;

import com.yanglf.usermanage.vo.BlogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "elasticsearch-servier")
public interface BlogClient {

    /**
     *
     * @param blog
     * @return
     */
    @PostMapping(value = "/blog")
    ResponseEntity save(@RequestBody BlogVO blog);
}
