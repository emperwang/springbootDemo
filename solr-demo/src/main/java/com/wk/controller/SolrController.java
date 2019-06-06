package com.wk.controller;

import com.wk.entity.TbItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SolrController {

    @Autowired
    private SolrTemplate solrTemplate;

    private final String Collection="collection1";

    @GetMapping("/add.do")
    public String addItem() throws IOException, SolrServerException {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号手机专卖店");
        item.setTitle("华为 Meta9");
        //item.setPrice(new BigDecimal(2000))        solrTemplate.saveBean(Collection,item);
        solrTemplate.commit(Collection);
        return "Add success";
    }
    @GetMapping("/getById.do")
    public String getById(){
        TbItem item = solrTemplate.getById(Collection, 1, TbItem.class).get();
        System.out.println(item);
        return "get Success";
    }
    @GetMapping("/deleteById.do")
    public String deleteById(){
        solrTemplate.deleteByIds(Collection,"1");
        solrTemplate.commit(Collection);
        return "delete success";
    }
    @GetMapping("/addList.do")
    public String allList(){
        List<TbItem> lists = new ArrayList<>();
        for (int i = 10; i < 100; i++){
            TbItem item = new TbItem();
            item.setId(1L + i);
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L + i);
            item.setSeller("华为2号手机专卖店");
            item.setTitle("华为 Meta9");
            //item.setPrice(new BigDecimal(2000+i));
            lists.add(item);
        }
        solrTemplate.saveBeans(this.Collection,lists);
        solrTemplate.commit(this.Collection);
        return "add list success";
    }

    @GetMapping("/findByPage.do")
    public String findByPage(){
        Query query = new SimpleQuery("*:*");
        query.setOffset(20L);  //开始索引 默认0
        query.setRows(20);  //每页记录数
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(Collection, query, TbItem.class);
        System.out.println("总记录数:"+tbItems.getTotalElements());
        List<TbItem> list = tbItems.getContent();
        System.out.println(list);
        return "findByPage success";
    }

}
