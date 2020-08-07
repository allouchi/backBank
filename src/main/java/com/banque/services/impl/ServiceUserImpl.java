package com.banque.services.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.banque.entites.User;
import com.banque.repositories.UserRepository;
import com.banque.services.ServiceUser;

@Service
@Transactional
public class ServiceUserImpl implements ServiceUser {
	
	private UserRepository userRepository;	
	
	
	public ServiceUserImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUser(User user) {
		return userRepository.findByMailAndPassword(user.getMail(), user.getPassword());
	}
	
	@Override
	public User getPassword(String mail) {
		return userRepository.findByMail(mail);
	}

	@Override
	public void updateUserDateLastConnection(String lastConnection, long userId) {
		userRepository.updateUserDateLastConnection(lastConnection, userId);
		
	}

}
