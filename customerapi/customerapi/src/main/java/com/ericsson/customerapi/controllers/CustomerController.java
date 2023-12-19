package com.ericsson.customerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.customerapi.services.KeyCloakService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
	@Autowired
	private KeyCloakService keyCloakService;
	@PostMapping(path = "/",
			  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<?> getToken(@RequestBody String data){
				log.info("Received....."+data);
		this.keyCloakService.getKeyCloakToken();
		return ResponseEntity.status(HttpStatus.OK).body("Done.....");
	}

}
