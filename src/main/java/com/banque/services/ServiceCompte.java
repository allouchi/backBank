package com.banque.services;

import java.util.List;

import com.banque.entites.Compte;

public interface ServiceCompte {

	List<Compte> getAllComptes();

	List<Compte> getComptesById(int id);

	Compte addCompte(Compte cp);

	void updateCompte(Compte cp);

	void deleteCompte(String id);	
	
}
