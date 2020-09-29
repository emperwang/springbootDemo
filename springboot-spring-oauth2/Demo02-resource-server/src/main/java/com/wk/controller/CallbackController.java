package com.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CallbackController {
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;
    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    @GetMapping("/callback")
    public OAuth2AccessToken login(@RequestParam("code") String code){
        final AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(oAuth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oAuth2ClientProperties.getClientSecret());

        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAuthorizationCode(code);

        restTemplate.getOAuth2ClientContext().getAccessTokenRequest()
                .setPreservedState("http://127.0.0.1:9090/callback");

        restTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());

        return restTemplate.getAccessToken();
    }
}
