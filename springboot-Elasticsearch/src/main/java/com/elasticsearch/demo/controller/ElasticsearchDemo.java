package com.elasticsearch.demo.controller;

import com.elasticsearch.demo.entity.Goods;
import com.elasticsearch.demo.service.GoodsRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("search")
public class ElasticsearchDemo {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("save.do")
    @ResponseBody
    public String save(){
        Goods goods = new Goods(1L,"you bad","this is a good book");
        System.out.println("save function");
        goodsRepository.save(goods);
        return "success save";
    }

    @GetMapping("save2.do")
    @ResponseBody
    public String save2(String name){
        Goods goods = new Goods(1L,name,"this is a good book");
        System.out.println("save function 2");
        goodsRepository.save(goods);
        return "success save";
    }

    @GetMapping("delete.do")
    @ResponseBody
    public String delete(long id){
        Goods goods = new Goods();
        goods.setId(id);
        System.out.println("delete function");
        goodsRepository.delete(goods);
        return "success delete";
    }

    @GetMapping("update.do")
    @ResponseBody
    public String update(long id,String name,String desc){
        Goods goods = new Goods(id, name, desc);
        System.out.println("update function");
        goodsRepository.save(goods);
        return "update succcess";
    }

    @GetMapping("getOne.do")
    @ResponseBody
    public Goods getOne(long id){
        System.out.println("getOne function");
        Optional<Goods> byId = goodsRepository.findById(id);
        Goods goods = byId.get();
        return goods;
    }

    //每页数量
    private Integer pageSize = 10;

    @GetMapping("getGoodslist.do")
    @ResponseBody
    public List<Goods> getList(String name, @PageableDefault(page = 1,value = 10) Pageable pageable){
        System.out.println("getList function");
        //按照名字查找
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);

        //如果实体和数据的名称对应就会自动封装  pageable分页参数
        Iterable<Goods> goods = goodsRepository.search(queryBuilder, pageable);

        //Iterable 转 list
        ArrayList<Goods> goods1 = Lists.newArrayList(goods);

        return goods1;
    }

}

