package com.genericStore.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.genericStore.security.dao.IRolDAO;
import com.genericStore.security.entities.Rol;
import com.genericStore.security.enums.RolName;

/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
public class CreateRoles implements CommandLineRunner {
	
	   @Autowired
	    IRolDAO rolService;

	    @Override
	    public void run(String... args) throws Exception {
//	        Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
//	        Rol rolUser = new Rol(RolName.ROLE_USER);
//	        rolService.newRol(rolAdmin);
//	        rolService.newRol(rolUser);
//	         
	    }

}
