package com.wk.controller.router;

import com.wk.entity.UserVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RouterController {
    @Bean
    public RouterFunction<ServerResponse>  userListRouterFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"),
                new HandlerFunction<ServerResponse>() {
                    @Override
                    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                        // 查询列表
                        List<UserVo> result = new ArrayList<>();
                        for (int i=1; i<=3;i++) {
                            UserVo vo = new UserVo();
                            vo.setId(i);
                            vo.setUsername("zhangsan"+i);
                            result.add(vo);
                        }
                        return ServerResponse.ok().bodyValue(result);
                    }
                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> userGetRouterFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/users2/get"),
                new HandlerFunction<ServerResponse>() {
                    @Override
                    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                        final Integer id = serverRequest.queryParam("id")
                                .map(s -> StringUtils.isEmpty(s) ? null : Integer.valueOf(s)).orElseGet(()->1);
                        final UserVo userVo = new UserVo();
                        userVo.setId(id);
                        return ServerResponse.ok().bodyValue(userVo);
                    }
                }
        );
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/users2/demo"),
                serverRequest -> ServerResponse.ok().bodyValue("demo"));
    }
}
