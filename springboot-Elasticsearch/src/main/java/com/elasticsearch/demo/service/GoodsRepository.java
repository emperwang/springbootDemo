package com.elasticsearch.demo.service;

import com.elasticsearch.demo.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
