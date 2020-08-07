package com.banque.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banque.config.HttpStatusProperties;
import com.banque.entites.User;
import com.banque.exceptions.UserNotFoundException;
import com.banque.services.ServiceUser;

import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/userService")
@Log4j2
public class UserRestService {
	
	
	private ServiceUser serviceUser;	
	private HttpStatusProperties httpStatus;	
	
	public UserRestService(ServiceUser serviceUser) {
		this.serviceUser = serviceUser;
	}


	@Autowired
	public void setHttpStatus(HttpStatusProperties httpStatus) {
		this.httpStatus = httpStatus;
	}	
	
	@GetMapping(value = "/user")	
	@ApiOperation(value = "Récupère un utilisateur à partir de ses identifiants !!!")
	public ResponseEntity<User> getUser(@RequestParam("email") String email, @RequestParam("password") String password)	
	{	
				
		User user = new User();
		user.setMail(email);
		user.setPassword(password);
		User userInBase = serviceUser.getUser(user);
			
		if(userInBase == null) {
			String message = String.format(httpStatus.getStatus(), email); 
			throw new UserNotFoundException(message);
		}
		
		log.info("User ok with mail {}", userInBase.getMail());
		
		return ResponseEntity.ok(userInBase);		
	}
	
	@GetMapping(value = "/user/{mail}")	
	@ApiOperation(value = "Récupère le mot de passe d'un utilisateur à partir de son adresse mail !!!")
	public String getPassword(@PathVariable String mail) {	
		
		if(serviceUser.getPassword(mail) != null) {
			return serviceUser.getPassword(mail).getPassword();
		}
		return null;		
	}
	
	@PutMapping(value = "/user",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
	        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})	
	@ApiOperation(value = "Met à jour la date de la dernière connexion de l'utilisateur !!!")
	public ResponseEntity<String> updateUserDateLastConnexion(@RequestBody String userId) {				
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formatDateTime = now.format(formatter);		
		serviceUser.updateUserDateLastConnection(formatDateTime, Long.parseLong(userId));
		
		return ResponseEntity.ok("Dernière date de connexion : " + formatDateTime);		
	}
	

}
