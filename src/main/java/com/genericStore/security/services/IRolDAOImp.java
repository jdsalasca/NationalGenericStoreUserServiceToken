package com.genericStore.security.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genericStore.security.dao.IRolDAO;
import com.genericStore.security.entities.Rol;
import com.genericStore.security.enums.RolName;



@Service
@Transactional
public class IRolDAOImp implements IRolDAO{
	

	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Rol findRolByName(RolName name) {
	    String query = "FROM Rol WHERE rolNombre = :name";
	    List<Rol> lista = entityManager.createQuery(query)
	            .setParameter("name", name)
	            .getResultList();

	    if (lista.isEmpty()) {
	        return null;
	    }
		return lista.get(0);
	}

	@Override
	public void newRol(Rol rol) {
		entityManager.merge(rol);
		
	}

}
