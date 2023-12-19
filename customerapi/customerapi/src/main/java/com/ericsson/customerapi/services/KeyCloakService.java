package com.ericsson.customerapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KeyCloakService {
	@Autowired
	private RestTemplate restTemplate;
	 @Value("${clientId}")
	private String clientId;
	 @Value("${grantType}")
	private String grantType;
	 @Value("${clientName}")
	private String userName;
	 @Value("${password}")
	private String password;
	 @Value("${url}")
	private String url; 
	
	public void getKeyCloakToken() {

		//String encodeBytes = Base64.getEncoder().encodeToString(("testuser: password").getBytes());
		
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	       headers.add("Accept", "application/json");
	     
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	    map.add("client_id",clientId);
	    map.add("grant_type",grantType);
	    map.add("username",userName);
	    map.add("password",password);
	    
	    
	       
	       
	       HttpEntity entity = new HttpEntity<>(map, headers);
            log.info(entity.getBody().toString());
	    ResponseEntity<JsonNode> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(url, entity, JsonNode.class);
            log.info(responseEntity.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    
	     
	}
	

}
