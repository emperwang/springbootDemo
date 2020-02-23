package com.wk.conbtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping(value = "index")
    public String indexView(){

        return "index";
    }

    @GetMapping(value = "role1/{id}")
    public String role1(@PathVariable("id") String id){

        return "role1/role"+id;
    }

    @GetMapping(value = "role2/{id}")
    public String role2(@PathVariable("id") String id){

        return "role2/role"+id;
    }

    @GetMapping(value = "role3/{id}")
    public String role3(@PathVariable("id") String id){

        return "role3/role"+id;
    }
}
