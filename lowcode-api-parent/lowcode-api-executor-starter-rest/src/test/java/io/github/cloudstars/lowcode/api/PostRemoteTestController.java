
package io.github.cloudstars.lowcode.api;

import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用于测试API远程调用的Controller
 *
 * @author clouds
 */
@RestController
@RequestMapping("/remote/api/post")
public class PostRemoteTestController {

    @PostMapping("/0")
    public Object postSomeThing(@RequestBody PostBody postBody) {
        return JsonUtils.toJsonString(postBody);
    }

    public static class PostBody {
        private String a;

        private Map<String, Object> x;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Map<String, Object> getX() {
            return x;
        }

        public void setX(Map<String, Object> x) {
            this.x = x;
        }
    }
}
