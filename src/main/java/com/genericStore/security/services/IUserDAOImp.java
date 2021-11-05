package com.genericStore.security.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.genericStore.security.dao.IUserDAO;
import com.genericStore.security.entities.User;
import com.genericStore.security.models_dto.LoginUser;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


@Service
@Transactional
public class IUserDAOImp implements IUserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	

	@Override
	public List<User> listUsers() {
		
		return null;
	}




	@Override
	public void newUser(User user) {
		
		entityManager.merge(user);
		
	}




	@Override
	public User getUserByNick(String nick) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean validateCredentials(@Valid LoginUser loginUser) {
		
		
        String query = "FROM User WHERE email = :email OR nick= :email";
        List<User> lista = entityManager.createQuery(query)
                .setParameter("email", loginUser.getNick())
                .getResultList();

        if (lista.isEmpty()) {
            return false;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, loginUser.getPassword())) {
            return true;
        }
		
		return false;
	}

}
