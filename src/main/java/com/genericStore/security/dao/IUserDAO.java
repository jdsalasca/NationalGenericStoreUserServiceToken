package com.genericStore.security.dao;

import java.util.List;

import javax.validation.Valid;

import com.genericStore.security.entities.User;
import com.genericStore.security.models_dto.LoginUser;

public interface IUserDAO {
	
	
	List <User> listUsers ();
	
	void newUser (User user);
	
	User  getUserByNick (String nick);

	boolean validateCredentials(@Valid LoginUser loginUser);
	
	boolean existByEmail (String email);
	
	boolean existByNick (String nick);

}
