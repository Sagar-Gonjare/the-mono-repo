package org.dnyanyog.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestMapper {
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String convertToJsonString(Object object)
	{
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
}
