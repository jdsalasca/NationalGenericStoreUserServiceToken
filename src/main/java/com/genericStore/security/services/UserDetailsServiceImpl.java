package com.genericStore.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genericStore.security.dao.IUserDAO;
import com.genericStore.security.entities.User;
import com.genericStore.security.models_dto.PrincipalUser;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IUserDAO iUserDAO;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = iUserDAO.getUserByNick(username);
		
		return PrincipalUser.build(user);
	}

}
