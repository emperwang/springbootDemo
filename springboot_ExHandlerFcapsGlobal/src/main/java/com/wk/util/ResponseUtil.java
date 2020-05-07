package com.wk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    /**
     *  设置code 和message 为返回体
     * @param status
     * @param message
     * @return
     */
    public static ResponseEntity getResponse(HttpStatus status,String message){
        Map<String,String> map = new HashMap<>();
        map.put("code",status.toString());
        map.put("message",message);
        log.debug( "response msg:{}, code:{}",message, status.toString());
        return new ResponseEntity(map,status);
    }

    public static ResponseEntity getResponseWithNoCode(HttpStatus status,String message){
        log.debug( "response msg:{}, code:{}",message, status.toString());
        return new ResponseEntity(message,status);
    }

    public static ResponseEntity getResponse(HttpStatus status,Map mapParam){
        Map<String,String> map = new HashMap<>();
        map.putAll(mapParam);
        log.debug( "response msg:{}, code:{}",JSONUtil.beanToJson(map), status.toString());
        return new ResponseEntity(map,status);
    }

    public static ResponseEntity getResponse(HttpStatus status){
        log.debug("code ={}", status.toString());
        return new ResponseEntity(status);
    }

    /**
     *  根据响应码的不同，来确定具体的返回体
     * @param response
     * @return
     */
    public static ResponseEntity getResponse(String response){
        if (response != null && response.contains("code") && response.contains("message")) {
            Map<String,String> map = new HashMap<>(2);
            String code = JSONUtil.getJsonField(response, "code");
            String message = JSONUtil.getJsonField(response, "message");
            if ("200".equalsIgnoreCase(code) || "201".equalsIgnoreCase(code)){
                if (response.contains("collectorId")){
                    String collectorId = JSONUtil.getJsonField(response, "collectorId");
                    map.put("collectorId",collectorId);
                    log.debug( "response msg:{}, code:{}",JSONUtil.beanToJson(map), code);
                    return new ResponseEntity(map,HttpStatus.CREATED);
                }else if (response.contains("taskId")){
                    String taskId = JSONUtil.getJsonField(response, "taskId");
                    map.put("taskId",taskId);
                    log.debug( "response msg:{}, code:{}",JSONUtil.beanToJson(map), code);
                    return new ResponseEntity(map,HttpStatus.CREATED);
                }else {
                    log.debug("code=201");
                    return new ResponseEntity(HttpStatus.CREATED);
                }
            }else{
                map.put("code",code);
                map.put("message",message);
                try {
                    int cod = Integer.parseInt(code);
                    HttpStatus status = HttpStatus.resolve(cod);
                    if (status != null) {
                        return new ResponseEntity(map, status);
                    } else {
                        if (cod >= 400 && cod < 500) {
                            log.debug("response:{}, code:{}", JSONUtil.beanToJson(map),400);
                            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
                        } else if (cod > 500) {
                            log.debug("response:{}, code:{}", JSONUtil.beanToJson(map),500);
                            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
                        } else {
                            log.debug("response:{}, code:{}", JSONUtil.beanToJson(map),200);
                            return new ResponseEntity(map, HttpStatus.OK);
                        }
                    }
                }catch (Exception e){
                    log.error("getResponse Exception: {}",e.getMessage());
                    log.debug("response:{}, code:{}", JSONUtil.beanToJson(map),500);
                    return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        log.error("getResponse with string, the string is :{}",response);
        log.debug("response:{}, code:{}", response,400);
        return new ResponseEntity("the response format is error,response is:"+response,HttpStatus.BAD_REQUEST);
    }

    /**
     *  设置header
     * @param status
     * @param message
     * @param headers
     * @return
     */
    public static ResponseEntity getResponse(HttpStatus status, String message, HttpHeaders headers){
        Map<String,String> map = new HashMap<>();
        map.put("code",status.toString());
        map.put("message",message);
        log.debug( "response msg:{}, code:{}",message, status.toString());
        return new ResponseEntity(map,headers,status);
    }
}
