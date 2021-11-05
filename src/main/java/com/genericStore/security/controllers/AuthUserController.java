package com.genericStore.security.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genericStore.models_dto.Message;
import com.genericStore.security.dao.IUserDAO;
import com.genericStore.security.entities.User;
import com.genericStore.security.jwt.JwtProvider;
import com.genericStore.security.models_dto.JwtDto;
import com.genericStore.security.models_dto.LoginUser;

@RestController
@CrossOrigin
@RequestMapping(path = "/auth")
public class AuthUserController {
	
	@Autowired
	private IUserDAO iUserDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		if(!iUserDAO.validateCredentials(loginUser)) {
			 return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
			
		}
//	    if(bindingResult.hasErrors())
//	        return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
	    Authentication authentication =
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNick(), loginUser.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtProvider.generateToken(authentication);
	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	    JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	    return new ResponseEntity(jwtDto, HttpStatus.OK);

}


}