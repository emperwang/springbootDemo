package com.stu.manage.entity;

/**
 * @author: Sparks
 * @Date: 2024/1/21 12:25
 * @Description
 */
public class ImageResponse {

    private String name;
    private String url;

    public ImageResponse() {
    }

    public ImageResponse(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public ImageResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ImageResponse setUrl(String url) {
        this.url = url;
        return this;
    }
}
