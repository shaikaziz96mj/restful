package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.domain.HelloWorldDomain;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public HelloWorldDomain sayHello() {
		return new HelloWorldDomain("Hello World");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldDomain sayHelloPathVariable(@PathVariable String name) {
		return new HelloWorldDomain(String.format("Hello World, %s", name));
	}
	
	/*@GetMapping(path = "/hello-world-internationalized")
	public String sayHelloInternationalized(@RequestHeader(name="Accept-Language",required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}*/
	
	@GetMapping(path = "/hello-world-internationalized")
	public String sayHelloInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
}