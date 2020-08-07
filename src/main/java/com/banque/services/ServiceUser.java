package com.banque.services;

import com.banque.entites.User;

public interface ServiceUser {

	User getUser(User user);

	User getPassword(String mail);
	
	void updateUserDateLastConnection(String lastConnection, long userId);

}
