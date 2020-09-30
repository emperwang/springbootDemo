package com.wk.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @author: wk
 * @Date: 2020/9/30 15:25
 * @Description
 */
@Slf4j
public class WebSocketShakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("before hande shake");
        // 获取 accessToken
        if (request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest)request;
            final String accessToken = servletServerHttpRequest.getServletRequest()
                    .getParameter("accessToken");
            attributes.put("accessToken",accessToken);
            log.info("[beforeHandshake][accessToken:{}]", accessToken);
        }
        // 继续执行后续逻辑
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
