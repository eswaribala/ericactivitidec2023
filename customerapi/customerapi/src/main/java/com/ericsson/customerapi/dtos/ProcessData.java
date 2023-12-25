package com.ericsson.customerapi.dtos;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessData {

	private String startUrl;

 private String processDefinitionKey;

 private String  payloadType;

 private String commandType;
 private Map<String, String> variables;

}
