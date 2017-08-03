# 08-practice-oauth2
sprint boot, sprint security, jwt, oauth2

workable oauth2 with spring boot 1.5.2

1-minimal-workable-version -- https://github.com/brotherhui/08-practice-oauth2/tree/master/1-minimal-workable-version
* set up the auth, client, resource server seperatly. Work with EnableOauth2Client

2-client2sso-version  -- https://github.com/brotherhui/08-practice-oauth2/tree/master/2-client2sso-version
* change from enableOauth2client to EnableOauth2Sso

3-3-clientcredentials-without-passwordprompt  -- https://github.com/brotherhui/08-practice-oauth2/tree/master/3-clientcredentials-without-passwordprompt
* change it from default Authhorization Code mode to client credentials mode, no password needed, suit for microsevices


# Please refer to below links:
http://docs.spring.io/spring/docs/5.0.0.RC3/spring-framework-reference/web.html#spring-web

http://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/htmlsingle/#boot-features-security-oauth2-resource-server

http://projects.spring.io/spring-security-oauth/docs/oauth2.html

http://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/htmlsingle/#boot-features-security-oauth2-authorization-server

