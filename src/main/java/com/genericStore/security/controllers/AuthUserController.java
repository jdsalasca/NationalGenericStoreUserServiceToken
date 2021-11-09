package com.genericStore.security.controllers;

import java.io.Console;
import java.util.HashSet;
import java.util.Set;

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
import com.genericStore.security.dao.IRolDAO;
import com.genericStore.security.dao.IUserDAO;
import com.genericStore.security.entities.Rol;
import com.genericStore.security.entities.User;
import com.genericStore.security.enums.RolName;
import com.genericStore.security.jwt.JwtProvider;
import com.genericStore.security.models_dto.JwtDto;
import com.genericStore.security.models_dto.LoginUser;
import com.genericStore.security.models_dto.NewUser;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

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
    
    @Autowired
    private IRolDAO iRolDAO;
    
    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Please check the fiels and the email"), HttpStatus.BAD_REQUEST);
        if(iUserDAO.existByNick(newUser.getNick()))
            return new ResponseEntity(new Message("That UserName is already in use"), HttpStatus.BAD_REQUEST);
        if(iUserDAO.existByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("That Email is already in use"), HttpStatus.BAD_REQUEST);
//		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//		String hash = argon2.hash(1, 1024, 1, newUser.getPassword());
//		newUser.setPassword(hash);
        User user =
                new User(newUser.getName(), newUser.getNick(), newUser.getEmail(), newUser.getIdentityDocument(),
                		newUser.getPassword());
        Set<Rol> roles = new HashSet<>();
        roles.add(iRolDAO.findRolByName(RolName.ROLE_USER));
        if(newUser.getRoles().contains("admin"))
            roles.add(iRolDAO.findRolByName(RolName.ROLE_ADMIN));
        user.setRoles(roles);
        iUserDAO.newUser(user);
        return new ResponseEntity(new Message("User created"), HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
		if(!iUserDAO.validateCredentials(loginUser)) {
			 return new ResponseEntity(new Message("please check all fields"), HttpStatus.BAD_REQUEST);
			
		}
		
//	    if(bindingResult.hasErrors())
//	        return new ResponseEntity(new Message("Fields Wrong"), HttpStatus.BAD_REQUEST);
	    Authentication authentication =
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNick(), loginUser.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    
	    String jwt = jwtProvider.generateToken(authentication);
	    UserDetails userDetails = (UserDetails)authentication.getPrincipal();
	    JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	    return new ResponseEntity(jwtDto, HttpStatus.OK);

}


}