package net.cf.api.proxy.engine.send;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/27 17:46
 */
public interface HttpPoster {
    ResponseEntity send(RequestEntity requestEntity);
}
