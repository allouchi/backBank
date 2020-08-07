package com.banque.services.impl;

import org.springframework.stereotype.Service;

import com.banque.entites.Contact;
import com.banque.repositories.ContactRepository;
import com.banque.services.ServiceContact;


@Service
public class ServiceContactImpl implements ServiceContact{

	
	private ContactRepository contactService;	
	
	
	public ServiceContactImpl(ContactRepository contactService) {		
		this.contactService = contactService;
	}

	@Override
	public Contact addContact(Contact c) {
		contactService.save(c);
		return null;
	}

	@Override
	public void updateContact(Contact cp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteContact(String id) {
		// TODO Auto-generated method stub
		
	}

}
