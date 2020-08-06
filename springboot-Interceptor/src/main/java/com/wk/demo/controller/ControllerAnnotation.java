package com.wk.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface ControllerAnnotation {
    // 可见 注解放到这里也是可以生效的
    @GetMapping("anno.do")
    String test1();
}
