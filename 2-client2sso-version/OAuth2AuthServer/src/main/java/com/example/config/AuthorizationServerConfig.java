package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Value("${config.oauth2.publicKey}")
    private String publicKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    //JWT Token is used for resource when it parsing the token also.
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        log.info("Initializing JWT with public key:\n" + publicKey);
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    /**
     * Defines the security constraints on the token endpoints /oauth/token_key and /oauth/check_token
     * Client credentials are required to access the endpoints
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    	//Some very important explaintion here:
    	//As Resource will get the token_key at starting phase to parse the access token. 
    	//so isAnonymous is used to ensure it can get the tokenKey successfully.
    	//If you can add security of the user for resource server, then you can use the define like hasRole('ROLE_XXXXX')
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//      oauthServer
//		.tokenKeyAccess("isAnonymous()  ||  hasRole('ROLE_CONFIDENTIAL_CLIENT') || hasRole('ROLE_TRUSTED_CLIENT')")
//        .tokenKeyAccess("hasRole('ROLE_CONFIDENTIAL_CLIENT') || hasRole('ROLE_TRUSTED_CLIENT')") // permitAll()
//        .checkTokenAccess("hasRole('ROLE_TRUSTED_CLIENT') || hasRole('ROLE_CONFIDENTIAL_CLIENT')") // isAuthenticated()
//        ;
    }

    /**
     * Defines the authorization and token endpoints and the token services
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints

                // Which authenticationManager should be used for the password grant
                // If not provided, ResourceOwnerPasswordTokenGranter is not configured
                .authenticationManager(authenticationManager)

                // Use JwtTokenStore and our jwtAccessTokenConverter
                .tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer())
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()

                // Confidential client where client secret can be kept safe (e.g. server side)
                .withClient("confidential").secret("secret")
                .authorities("ROLE_CONFIDENTIAL_CLIENT")
                .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
                .scopes("read", "write", "openid")
//                .redirectUris("http://localhost:8080/client/door") //this redirectUri's propose is to limit the caller uri

                .and()

                // Public client where client secret is vulnerable (e.g. mobile apps, browsers)
                .withClient("public") // No secret!
                .authorities("ROLE_PUBLIC_CLIENT")
                .authorizedGrantTypes("client_credentials", "implicit")
                .scopes("read")
//                .redirectUris("http://localhost:8080/client/door") //this redirectUri's propose is to limit the caller uri

                .and()

                // Trusted client: similar to confidential client but also allowed to handle user password
                .withClient("trusted").secret("secret")
                .authorities("ROLE_TRUSTED_CLIENT")
                .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
                .scopes("read", "write", "openid")
//                .redirectUris("http://localhost:8080/client/door") //this redirectUri's propose is to limit the caller uri
        ;
    }

}
