package com.genericStore.security.dao;

import java.util.List;

import com.genericStore.security.entities.Rol;


public interface IRolDAO {
	
	List <Rol> findRolByName (String name);
	
	void newRol (Rol rol);

}
