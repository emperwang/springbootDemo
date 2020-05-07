package com.wk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.util.JSONUtil;
import org.junit.Test;

public class jsonTest {

    @Test
    public void isJson(){
        String js = "code:500";
        //JSONObject object = JSON.parseObject(js);
        System.out.println(JSONUtil.isJson(js));
    }


    @Test
    public void json(){
        String js = "{\"code\":500,\"message\":\"error\" }";
        // JSON.parseObject(js);
        System.out.println(JSONUtil.isJson(js));
    }
}
