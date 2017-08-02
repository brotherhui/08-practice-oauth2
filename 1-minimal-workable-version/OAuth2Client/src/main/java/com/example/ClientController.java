package com.example;

import com.fasterxml.jackson.databind.JsonNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ClientController {

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);
	
    @Autowired
    private OAuth2RestOperations restTemplate;

    @Value("${config.oauth2.resourceURI}")
    private String resourceURI;
    

    @RequestMapping("/door")
    public JsonNode door() {
    	log.info("I am calling resource");
        return restTemplate.getForObject(resourceURI, JsonNode.class);
    }
    

}