package com.wk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
// 声明开启OAuth 授权服务器功能
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 用户认证manager
    @Autowired
    private AuthenticationManager authenticationManager;
    // 配置endpoints 使用AuthenticationManager实现用户认证的功能,
    // 其中AuthenticationManager 是由securityConfig 创建
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
    // 设置 /oauth/check_token 端点,通过认证后可访问.
    // 这里的认证指的是使用 client-id+client-secret进行的客户端认证,和用户认证两码事
    // /oauth/check_token 端点对应 CheckTokenEndpoint类,用于校验访问令牌的有效性
    // 1. 在客户端访问资源服务器时,会在请求中带上访问令牌
    // 2. 在资源服务器收到客户端的请求时,会使用请求中的访问令牌,找授权服务器确认该访问令牌的有效性
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
    // 进行客户端的配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 设置基于内存的client 存储器
        clients.inMemory()
                // 创建一个client配置
                .withClient("clientapp").secret("112233")
                .authorizedGrantTypes("implicit")
                .redirectUris("http://127.0.0.1:9091/callback")
                .scopes("read_userinfo","read_contacts");
                // .and().withClient()  // 可以在后面继续进行配置
    }
}
