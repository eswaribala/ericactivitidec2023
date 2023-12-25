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
	 @Value("${processDefUrl}")
	private String processDefUrl;
	 @Value("${startUrl}")
	private String startUrl;
	 @Value("${processDefinitionKey}")
	 private String processDefinitionKey;
	 @Value("${payloadType}")
	 private String  payloadType;
	 @Value("${commandType}")
	 private String commandType;
	 @Value("${keycloak.user-info-uri}")
	    private String keycloakUserInfo;
	 
	public String kcAccessToken;
	
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
            kcAccessToken=recdToken.toString();
            log.info("Token"+kcAccessToken);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return responseEntity.getBody().toString();
	     
	}
	
	 public String checkValidity(String token) throws Exception {
	        return getUserInfo(token);
	    }

	public String getProcessDefinitions(String token) {
		
		

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
       // headers .add("client_id",clientId);
       // headers .add("grant_type",grantType);
       // headers .add("username",userName);
      //  headers .add("password",password);
	    
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);;
	        
        ResponseEntity<String> responseEntity=null;
        try {
	            responseEntity = restTemplate.
	            		exchange(processDefUrl,HttpMethod.GET, entity, String.class);
	            String jsonNode=responseEntity.toString();
	            System.out.println(jsonNode);
	           
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        return responseEntity.getBody().toString();
       
	}

	
	public String startProcess(String token) {
				
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);
        headers.add("Content-Type", "application/json");
       ProcessData processData = new ProcessData();
       processData.setProcessDefinitionKey(processDefinitionKey);
       processData.setPayloadType(payloadType);
       processData.setCommandType(commandType);
       processData.setVariables(null);
	   
       HttpEntity entity = new HttpEntity<>(processData, headers);
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
       
            return responseEntity.getBody().toString();
	}
	
	   private String getUserInfo(String token) {
	        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	        headers.add("Authorization", token);

	        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
	        return restTemplate.postForObject(keycloakUserInfo, request, String.class);
	    }
}
	
	
	


