package com.wk.collection;

import java.util.concurrent.ConcurrentHashMap;

public class CollectionUtil {


    public static <K,V> ConcurrentHashMap<K,V> newConcurrentHashMap(){
        return new ConcurrentHashMap<>();
    }
}
