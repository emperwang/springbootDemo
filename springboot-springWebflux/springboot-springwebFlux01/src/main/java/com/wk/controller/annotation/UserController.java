package com.wk.controller.annotation;

import com.wk.entity.UserAddDTO;
import com.wk.entity.UserUpdateDTO;
import com.wk.entity.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public Flux<UserVo> list(){
        // 查询列表
        List<UserVo> result = new ArrayList<UserVo>();
        for (int i=1; i<=3;i++) {
            UserVo vo = new UserVo();
            vo.setId(i);
            vo.setUsername("zhangsan"+i);
            result.add(vo);
        }
        return Flux.fromIterable(result);
    }

    /**
     * 获得指定用户编号的用户
     */
    @GetMapping("/get")
    public Mono<UserVo> get(@RequestParam("id") Integer id){
        log.info("[get] receive id: {}", id);
        final UserVo userVo = new UserVo();
        userVo.setId(id);
        userVo.setUsername("username");
        return Mono.just(userVo);
    }

    /**
     * 添加用户
     */
    @PostMapping("add")
    public Mono<Integer> add(@RequestBody Publisher<UserAddDTO> userAdd){
        //log.info("[add] receive info: {}", userAdd.toString());
        // 插入用户记录，返回编号
        Integer rid = 1;
        // 返回用户编号
        return Mono.just(rid);
    }

    @PostMapping("/add2")
    public Mono<Integer> add(Mono<UserAddDTO> add){
        final int rid = UUID.randomUUID().hashCode();
        return Mono.just(rid);
    }

    /**
     * 更新指定用户编号的用户
     */
    @PostMapping("/update")
    public Mono<Boolean> update(@RequestBody Publisher<UserUpdateDTO> user){
        //log.info("[update] receive info:{}", user.toString());
        Boolean success = true;
        return Mono.just(success);
    }

    /**
     * 删除指定用户编号的用户
     */
    @PostMapping("/delete")
    public Mono<Boolean> delete(@RequestParam("id") Integer id){
        log.info("[delete] receive info: {}", id);
        // 删除用户记录
        Boolean success = false;
        return Mono.just(success);
    }
}
