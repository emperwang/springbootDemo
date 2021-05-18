package com.test.wk.common;

import com.test.wk.entity.Role;
import com.test.wk.entity.User;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class Common {
    private static Set<User> userinfos = new HashSet<>();

    private static Set<Role> roles = new HashSet<>();

    private static Map<String,Long> tokens = new ConcurrentHashMap<>();


    public static Set<User> getUserinfos() {
        return userinfos;
    }

    public static Set<Role> getRoles() {
        return roles;
    }

    public static Map<String, Long> getTokens() {
        return tokens;
    }
}
