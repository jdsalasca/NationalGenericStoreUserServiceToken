package com.genericStore.security.dao;

import java.util.List;

import com.genericStore.security.entities.Rol;
import com.genericStore.security.enums.RolName;


public interface IRolDAO {
	
	Rol findRolByName (RolName rolName);
	
	void newRol (Rol rol);

}
