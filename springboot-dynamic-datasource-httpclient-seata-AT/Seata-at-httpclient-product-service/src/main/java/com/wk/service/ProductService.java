package com.wk.service;

public interface ProductService {
    void reduceStock(Long produceId, Integer amount) throws Exception;
}
