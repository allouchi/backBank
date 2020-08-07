package com.banque.services;

import com.banque.entites.Contact;

public interface ServiceContact {
	
	Contact addContact(Contact c);

	void updateContact(Contact c);

	void deleteContact(String id);

}
