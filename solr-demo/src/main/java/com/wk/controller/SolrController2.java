package com.wk.controller;

import com.wk.entity.TbItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/solrclient")
public class SolrController2 {

    @Autowired
    private SolrClient solrClient;
    private final String Collection="collection1";

    // 添加一条记录
    @GetMapping("/add.do")
    public String addItem() throws IOException, SolrServerException {
        TbItem item = new TbItem();
        item.setId(1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号手机专卖店");
        item.setTitle("华为 Meta9");
        solrClient.addBean(this.Collection,item);
        solrClient.commit();
        return "client add success";
    }
    // 通过id查询记录
    @GetMapping("findById.do")
    public String findById() throws IOException, SolrServerException {
        SolrDocument document = solrClient.getById(this.Collection, "80");
        int childDocumentCount = document.getChildDocumentCount();
        java.util.Collection<String> fieldNames = document.getFieldNames();
        Map<String, java.util.Collection<Object>> valuesMap = document.getFieldValuesMap();
        System.out.println("childDocumentCount "+childDocumentCount);
        System.out.println("fieldNames "+fieldNames);
        System.out.println("valuesMap "+valuesMap);
        return "client getById success";
    }

    // 通过id删除一条记录
    @GetMapping("delete.do")
    public String deleteById() throws IOException, SolrServerException {
        UpdateResponse response = solrClient.deleteById(this.Collection, "1");

        System.out.println(response.toString());
        return "client delete success";
    }

    // 查询
    @GetMapping("/query.do")
    public String clientQuery() throws IOException, SolrServerException {
        // 第一种方式
        Map<String,String> queryParamMap = new HashMap<String,String>();
        queryParamMap.put("q","*:*");
        queryParamMap.put("f1","id,name");
        queryParamMap.put("sort","id asc");
        MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
        QueryResponse response = solrClient.query(mapSolrParams);
        SolrDocumentList results = response.getResults();
        System.out.println(response);

        // 第二种方式
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.addField("*");
        solrQuery.add("q","id:1");
        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        //设置查询条数
        solrQuery.setRows(20);
        //设置查询的开始
        solrQuery.setStart(0);
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮字段
        solrQuery.addHighlightField("item_title");
        //设置高亮样式
        solrQuery.setHighlightSimplePre("<font colr='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        System.out.println(solrQuery);
        QueryResponse response1 = solrClient.query(solrQuery);
        //返回高亮结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //返回查询结果
        SolrDocumentList results1 = response1.getResults();
        System.out.println(results1);

        return "query success";
    }

}
