package com.genericStore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genericStore.security.dao.IUserDAO;
import com.genericStore.security.entities.User;




@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	
	@Autowired
	private IUserDAO iUserDAO;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<User> getUsuarioListA() {
		
		
		
		return iUserDAO.getUserList();
	}
	
	


}
