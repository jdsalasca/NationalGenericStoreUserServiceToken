package com.genericStore.security.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
    @Autowired
    PasswordEncoder passwordEncoder;
	
	





	@Override
	public void newUser(User user) {
		
		entityManager.merge(user);
		
	}




	@Override
	public User getUserByNick(String nick) {
		 String query = "FROM User WHERE email = :email OR nick= :email";
	        List<User> lista = entityManager.createQuery(query)
	                .setParameter("email",nick)
	                .getResultList();
		
		return lista.get(0);
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

        if (passwordEncoder.matches(loginUser.getPassword(),lista.get(0).getPassword())) {
            return true;
        }
		
		return false;
	}
	@Override
	public boolean existByEmail(String email) {
		String query = "From User WHERE email= :email";
		List <User> lista = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();
		if (lista.isEmpty()) {
			return false;
		}
		
		return true;
	}




	@Override
	public boolean existByNick(String nick) {
		String query = "From User WHERE nick = :nick";
		List <User> lista = entityManager.createQuery(query)
				.setParameter("nick", nick)
				.getResultList();
		if (lista.isEmpty()) {
			return false;
		}
		return true;
	}




	@Override
	public List<User> getUserList() {
		String query = "FROM User";
		return entityManager.createQuery(query).getResultList();	
	}

}
