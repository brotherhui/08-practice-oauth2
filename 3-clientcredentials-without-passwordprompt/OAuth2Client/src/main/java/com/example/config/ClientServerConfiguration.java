package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
@EnableAutoConfiguration
public class ClientServerConfiguration {
	
	
	@Value("${security.oauth2.client.clientId}")
    private String clientId;
	@Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;
	@Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;
	@Value("${security.oauth2.client.userAuthorizationUri}")
    private String userAuthorizationUri;
	@Value("${security.oauth2.client.scope}")
    private String scope;
    
    
    /**
     * The heart of our interaction with the resource; handles redirection for authentication, access tokens, etc.
     * @param oauth2ClientContext
     * @return
     */
    
//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
//            OAuth2ProtectedResourceDetails details) {
//    	//please notice that if we didn't nomiate the ResourceDetails, it will use Authoriztion code mode
//        return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }
    
    
    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {

    	//We are using client credentials mode
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        clientCredentialsResourceDetails.setAccessTokenUri(accessTokenUri);
        clientCredentialsResourceDetails.setScope(Arrays.asList(scope.split(",")));
        details = clientCredentialsResourceDetails;

        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }
}
