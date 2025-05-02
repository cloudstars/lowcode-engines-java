package io.github.cloudstars.lowcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试API Get请求的Controller
 *
 * @author clouds
 */
@RestController
@RequestMapping("/remote/api/get")
public class GetRemoteTestController {

    @GetMapping("/0")
    public Object getSomeThing(Object params) {
        return "SomeThing";
    }

}
