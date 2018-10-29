package com.yanglf.usermanage.esclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Administrator
 * @Date: 2018/10/27 23:21
 * @Description:
 */
@FeignClient(name = "elasticsearch-servier")
public interface ProductClient {

    @GetMapping("/msg")
    String msg();
}
