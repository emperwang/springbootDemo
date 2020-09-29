package com.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    /**
     * 在 login接口中,资源服务器扮演的是一个OAuth客户端的角色,调用服务器的/oauth/token接口,使用密码模式进行授权
     * 获得访问令牌.
     * ResourceOwnerPasswordResourceDetails 对象填写密码模式授权需要的请求参数
     * OAuth2RestTemplate 对象,是spring security oauth 封装的工具类,用于请求授权服务器
     * 同时 将ResourceOwnerPasswordAccessTokenProvider 设置到其中,表示使用密码模式授权,这一步非常重要
     *
     * restTemplate.getAccessToken 调用授权服务器的 /oauth/token接口,进行密码模式授权.
     */
    @PostMapping("/login")
    public OAuth2AccessToken login(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        // 创建ResourceOwnerPasswordResourceDetails 对象,保存用户信息
        final ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(oAuth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oAuth2ClientProperties.getClientSecret());
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        // 创建 OAuth2RestTemplate 对象
        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        // 获取访问令牌
        return restTemplate.getAccessToken();
    }
}





















