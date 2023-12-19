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

import com.ericsson.customerapi.dtos.ProcessData;
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
	 
	 @Value("${startUrl}")
		private String startUrl;
	 @Value("${processDefinitionKey}")
	 private String processDefinitionKey;
	 @Value("${payloadType}")
	 private String  payloadType;
	 @Value("${commandType}")
	 private String commandType;
	 
	 
	 private String token;
	
	public String getKeyCloakToken() {

		//String encodeBytes = Base64.getEncoder().encodeToString(("testuser: password").getBytes());
		
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	       headers.add("Accept", "application/json");
	      
	       
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
	    map.add("client_id",clientId);
	    map.add("grant_type",grantType);
	    map.add("username",userName);
	    map.add("password",password);
	    
	    
	       
	       
	       HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, headers);
            log.info(entity.getBody().toString());
	    ResponseEntity<JsonNode> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(url, entity, JsonNode.class);
            JsonNode jsonNode=responseEntity.getBody();
            JsonNode recdToken=jsonNode.get("access_token");     
            token=recdToken.toString();
            log.info("Token"+token);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return responseEntity.getBody().toString();
	     
	}
	
	public String startProcess() {
				
				HttpHeaders headers = new HttpHeaders();
			       headers.setContentType(MediaType.APPLICATION_JSON);
			       headers.add("Accept", "application/json");
			       headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			       
			       
			       MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				    map.add("processDefinitionKey",processDefinitionKey);
				    map.add("payloadType",payloadType);
				    map.add("commandType",commandType);
				    map.add("variables",null);			       
				    map.add("client_id",clientId);
				    map.add("grant_type",grantType);
				    map.add("username",userName);
				    map.add("password",password);
			       HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, headers);
		            log.info(entity.getBody().toString());
		            
			    ResponseEntity<JsonNode> responseEntity = null;
		        try {
		            responseEntity = restTemplate.postForEntity(startUrl, entity, JsonNode.class);
		           // JsonNode jsonNode=responseEntity.getBody();
		          //  JsonNode recdToken=jsonNode.get("access_token");     
		            //token=recdToken.toString();
		            log.info("Process Created");
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		       
		            return token;
	}
	
	
	

}
