package com.genericStore.security.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genericStore.security.dao.IRolDAO;
import com.genericStore.security.entities.Rol;



@Service
@Transactional
public class IRolDAOImp implements IRolDAO{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Rol> findRolByName(String name) {
	    String query = "FROM Usuario WHERE rolNombre = :name";
	    List<Rol> lista = entityManager.createQuery(query)
	            .setParameter("name", name)
	            .getResultList();

	    if (lista.isEmpty()) {
	        return null;
	    }
		return lista;
	}

	@Override
	public void newRol(Rol rol) {
		entityManager.merge(rol);
		
	}

}
