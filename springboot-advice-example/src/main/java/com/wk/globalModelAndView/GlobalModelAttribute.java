package com.wk.globalModelAndView;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalModelAttribute {

    @ModelAttribute("msg")
    public String msg(){
        return "this is msg";
    }

    @ModelAttribute("info")
    public Map<String,String> userInfo(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name","hangge");
        map.put("age", "100");
        return map;
    }
}
