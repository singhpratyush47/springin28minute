package com.springrestful.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springrestful.domain.SomeBean;

@RestController
public class FilteringController {


	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("field1", "field2", "field3");
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue retrieveSomeBean1() {
		SomeBean bean=new SomeBean("field1", "field2", "field3");
		
		SimpleBeanPropertyFilter beanPropertyFilter=SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		FilterProvider filters=new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", beanPropertyFilter);
		MappingJacksonValue value=new MappingJacksonValue(bean);
		value.setFilters(filters);
		return value;
	}

}
