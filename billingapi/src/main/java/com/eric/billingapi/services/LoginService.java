package com.eric.billingapi.services;

import java.util.Map;

import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service("loginService")
@Slf4j
public class LoginService implements Connector{

	@Override
	public IntegrationContext apply(IntegrationContext t) {
		// TODO Auto-generated method stub
	   Map<String,Object> variables=t.getInBoundVariables();
	   
		log.info("Accessing Login Service"+t.getProcessDefinitionKey());
		return t;
	}

}
