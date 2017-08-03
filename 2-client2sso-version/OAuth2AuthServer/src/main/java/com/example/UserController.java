package com.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    @RequestMapping("/user")
    public Principal user(Principal principal) throws JsonParseException, JsonMappingException, IOException {
        return principal;
    }
    

}