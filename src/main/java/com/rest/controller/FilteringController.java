package com.rest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.domain.SampleBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSampleBean() {
		SampleBean sampleBean = new SampleBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","filed2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SampleBeanFilter", filter);
		
		MappingJacksonValue mapping=new MappingJacksonValue(sampleBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public List<SampleBean> retrieveListOfSampleBean() {
		return Arrays.asList(new SampleBean("value1", "value2", "value3"),
				new SampleBean("value11", "value22", "value33"));
	}
	
}