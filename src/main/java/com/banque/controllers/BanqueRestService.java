package com.banque.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.banque.entites.Compte;
import com.banque.services.ServiceCompte;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/banque")
@Api(tags = {"API pour les opérations CRUD sur les comptes bancaires."})
public class BanqueRestService {	
	
	private ServiceCompte serviceCompte;
	
	public BanqueRestService(ServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}    

	@ApiOperation(value = "Récupère tous les comptes bancaires !!!")
	@GetMapping(value ="/comptes")
	public Compte[] getComptes()	{
		List<Compte> listeCompte = serviceCompte.getAllComptes();
		Compte [] tabCompte = null;
		
		if( !listeCompte.isEmpty()) {
			tabCompte = new Compte[listeCompte.size()];		    
			tabCompte = listeCompte.toArray(tabCompte);
		}
				
		return tabCompte;
	}
	
	@ApiOperation(value = "Récupère les comptes bancaires par utilisateur !!!")
	@GetMapping(value ="/comptes/{userId}")
	public Compte[] getComptesByUserId(@PathVariable int userId)	{
				
		List<Compte> listeCompte = serviceCompte.getComptesById(userId);
		Compte [] tabCompte = null;
		
		if( !listeCompte.isEmpty()) {
			tabCompte = new Compte[listeCompte.size()];		    
			tabCompte = listeCompte.toArray(tabCompte);	
			
		}				
		return tabCompte;
	}
	
	
	@ApiOperation(value = "Ajoute un compte bancaire !!!")
	@PostMapping(value = "/compte", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Compte> addCompte(@RequestBody Compte cp)
	{		
		serviceCompte.addCompte(cp);
		return ResponseEntity.ok(cp);
	}	
		
	@PutMapping(value = "/compte", consumes = "application/json" , produces = "application/json")
	@ApiOperation(value = "Modifie un compte bancaire!")
	public ResponseEntity<String> update(@RequestBody final Compte cp)
	{				
		serviceCompte.updateCompte(cp);
		return ResponseEntity.ok("Compte mis à jour avec succès");
	}	
	
	@DeleteMapping(value = "/compte/{code}")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Supprime un compte un compte bancaire à partir de son numéro !!!")
	public ResponseEntity<String> delete(@PathVariable String code)
	{		
		serviceCompte.deleteCompte(code);
		String message = String.format("Le compte numéro %s est supprimé avec succès", code);
		return ResponseEntity.ok(message);
	}	
	
}
