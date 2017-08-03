This is based on Spring boot 1.5.2 and workable version
Worked with EnableOauth2Sso

### Guide
Please follow the same introduction
https://github.com/brotherhui/08-practice-oauth2/tree/master/1-minimal-workable-version



### The key point is :

In client-resource/application.yml

add

'
        resource:
           token-info-uri: http://localhost:8081/oauth/check_token
'



add
@EnableOAuth2Sso



