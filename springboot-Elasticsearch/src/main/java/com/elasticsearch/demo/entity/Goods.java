package com.elasticsearch.demo.entity;

import org.springframework.data.elasticsearch.annotations.Document;

//这里的indexName必须小写
//type 可以理解为表明  indexName 数据库名
@Document(indexName = "testgoods",type = "goods")
public class Goods {

    private Long id;

    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goods(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Goods() {
    }
}
