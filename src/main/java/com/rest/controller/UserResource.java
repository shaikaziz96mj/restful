package com.rest.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.dao.UserDaoService;
import com.rest.domain.User;
import com.rest.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userService.findAll(); 
	}
	
	@GetMapping("/user/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Long id) {
		User findOne = userService.findOne(id);
		if(findOne==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
		EntityModel<User> resource=EntityModel.of(findOne);
		WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("users"));
		
		return resource;
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saved=userService.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(saved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id) {
		User findOne = userService.deleteById(id);
		if(findOne==null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
	
}