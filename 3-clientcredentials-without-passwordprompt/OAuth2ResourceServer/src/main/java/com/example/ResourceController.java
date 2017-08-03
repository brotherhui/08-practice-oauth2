package com.example;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ResourceController {
	
	@Autowired
	private ObjectMapper objectMapper;

//	@PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/user")
    public Principal user(Principal principal) throws JsonParseException, JsonMappingException, IOException {
        return principal;
    }
	
//	@PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/jwt")
    public Object jwt(Principal principal) throws JsonParseException, JsonMappingException, IOException {
    	OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
    	String jwtValue = details.getTokenValue();
    	String payloadBase64 = jwtValue.split("\\.")[1];
    	Map<String,Object> payload = objectMapper.readValue(Base64Utils.decodeFromString(payloadBase64), new TypeReference<Map<String,Object>>() {
    	});
    	Map<String,Object> response = new LinkedHashMap<>();
    	response.put("message", "here's the JWT payload");
    	response.put("payload", payload);
    	return response;
    }
	


}
