package com.hooman.incident.JsonConversion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hooman.incident.request.IncidentRequest;

public class JsonDataGenerator {
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		IncidentRequest obj = new IncidentRequest();
		obj.setTenantId(1);
		obj.setSubject("test");
		obj.setCriteria1("criteria1");
		obj.setCriteria2("criteria2");
		obj.setCriteria3("criteria3");
		obj.setCriteria4("criteria4");
		obj.setCriteria5("criteria5");
		obj.setCriteria6("criteria6");
		obj.setCriteria7("criteria7");
		obj.setCriteria8("criteria8");
		obj.setCriteria9("criteria9");
		obj.setCriteria10("criteria10");
		List<String> list = new ArrayList<String>();
		list.add("1");
		obj.setAssignedTeamIds(list);
		// Object to JSON in String
		String jsonInString = mapper.writeValueAsString(obj);
		System.out.println(jsonInString);
		
	}
}
