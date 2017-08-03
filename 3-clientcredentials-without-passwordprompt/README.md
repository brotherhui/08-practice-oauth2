This is based on Spring boot 1.5.2 and workable version

This version is no longer work with password when you call http://localhost:8080/client/door it changes from Authorization Code mode to Client Credentials. 

### OAuth2 Client Key change is this part 

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


