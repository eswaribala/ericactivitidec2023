package com.ericsson.customerapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	private String clientId;
	private String grantType;
	private String username;
	private String password;

}
