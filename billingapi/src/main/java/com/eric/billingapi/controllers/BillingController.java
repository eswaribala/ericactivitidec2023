package com.eric.billingapi.controllers;

import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
	@Autowired
	private RuntimeService runTimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskRuntime taskRunTime;
	
	@GetMapping("/start")
	public ResponseEntity<?> startProcess(){
	ProcessInstance processInstance=runTimeService.startProcessInstanceByKey("Process_qIZXOHi6h");	
		
		return ResponseEntity.status(HttpStatus.OK).body("Process Started......"+processInstance.getDeploymentId());
	}

}
