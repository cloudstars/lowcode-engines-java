package io.github.cloudstars.lowcode.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试API远程调用的Controller
 *
 * @author clouds
 */
@RestController
@RequestMapping("/remote/api")
public class RemoteTestController {

    @GetMapping("/get")
    public Object getSomeThing(Object params) {
        return "SomeThing";
    }

}
