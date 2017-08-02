This is based on Spring boot 1.5.2 and workable version

### OAuth2 Server Config

Generate JWT public key and private key

        # openssl genrsa -out jwt.pem 2048
        # openssl rsa -in jwt.pem

            -----BEGIN RSA PRIVATE KEY-----
            MIICXQIBAAKBgQDNQZKqTlO/+2b4ZdhqGJzGBDltb5PZmBz1ALN2YLvt341pH6i5
            mO1V9cX5Ty1LM70fKfnIoYUP4KCE33dPnC7LkUwE/myh1zM6m8cbL5cYFPyP099t
            hbVxzJkjHWqywvQih/qOOjliomKbM9pxG8Z1dB26hL9dSAZuA8xExjlPmQIDAQAB
            AoGAImnYGU3ApPOVtBf/TOqLfne+2SZX96eVU06myDY3zA4rO3DfbR7CzCLE6qPn
            yDAIiW0UQBs0oBDdWOnOqz5YaePZu/yrLyj6KM6Q2e9ywRDtDh3ywrSfGpjdSvvo
            aeL1WesBWsgWv1vFKKvES7ILFLUxKwyCRC2Lgh7aI9GGZfECQQD84m98Yrehhin3
            fZuRaBNIu348Ci7ZFZmrvyxAIxrV4jBjpACW0RM2BvF5oYM2gOJqIfBOVjmPwUro
            bYEFcHRvAkEAz8jsfmxsZVwh3Y/Y47BzhKIC5FLaads541jNjVWfrPirljyCy1n4
            sg3WQH2IEyap3WTP84+csCtsfNfyK7fQdwJBAJNRyobY74cupJYkW5OK4OkXKQQL
            Hp2iosJV/Y5jpQeC3JO/gARcSmfIBbbI66q9zKjtmpPYUXI4tc3PtUEY8QsCQQCc
            xySyC0sKe6bNzyC+Q8AVvkxiTKWiI5idEr8duhJd589H72Zc2wkMB+a2CEGo+Y5H
            jy5cvuph/pG/7Qw7sljnAkAy/feClt1mUEiAcWrHRwcQ71AoA0+21yC9VkqPNrn3
            w7OEg8gBqPjRlXBNb00QieNeGGSkXOoU6gFschR22Dzy
            -----END RSA PRIVATE KEY-----

        # openssl rsa -in jwt.pem -pubout

            -----BEGIN PUBLIC KEY-----
            MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNQZKqTlO/+2b4ZdhqGJzGBDlt
            b5PZmBz1ALN2YLvt341pH6i5mO1V9cX5Ty1LM70fKfnIoYUP4KCE33dPnC7LkUwE
            /myh1zM6m8cbL5cYFPyP099thbVxzJkjHWqywvQih/qOOjliomKbM9pxG8Z1dB26
            hL9dSAZuA8xExjlPmQIDAQAB
            -----END PUBLIC KEY-----

### OAuth2 Client Config
Just go to look at the resources/application.yml 
It contains nearly all the info for the client needs.

ResourceURI is the whatever you want from your resource server. It is just an example


### OAuth2 Resource Config
Just use the key_token url to initial the JWT token parser when starting the server.

### The concept like this:
1. Resource Owner : me
2. Resource Server : provide the my info
3. Auth Server : provide the access token and verify the user info
4. Client : client want to get the my resource, but it need to get resource owner(me)'s access token first



### Try it:
1. I want to access client use my auth
2. I clicked http://localhost:8080/client/door
3. It auto jumpt to auth server http://localhost:8081/login prompt me to input username and passowrd: user/user
4. It will get the auth information by http://localhost:8081/auth/token and redirect back to http://localhost:8080/client/door with access token.
5. http://localhost:8080/client/door will call http://localhost:8082/resource/jwt with the access token to see the jwt information.




