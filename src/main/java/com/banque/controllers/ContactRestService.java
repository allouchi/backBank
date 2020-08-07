package com.banque.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banque.entites.Contact;
import com.banque.entites.ContactWrapper;
import com.banque.services.ServiceContact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/contact")
public class ContactRestService {

	private ServiceContact serviceContact;	

	public ContactRestService(ServiceContact serviceContact) {
		this.serviceContact = serviceContact;
	}



	@PostMapping(value = "/addContact", consumes = { "multipart/form-data" })
	public String addContact(@RequestPart("file") MultipartFile file, @RequestPart("contact") String form) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			ContactWrapper cWrapper = mapper.reader().forType(ContactWrapper.class).readValue(form);
			byte[] myBytes = file.getBytes();
			Blob myBlob = new SerialBlob(myBytes);

			Contact contact = new Contact();
			contact.setEmail(cWrapper.getMail());
			contact.setComment(cWrapper.getComment());
			contact.setNumero(cWrapper.getNumero());
			contact.setData(myBlob);

			serviceContact.addContact(contact);
			

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SerialException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
