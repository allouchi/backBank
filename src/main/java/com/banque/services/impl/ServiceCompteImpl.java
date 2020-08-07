package com.banque.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.banque.entites.Compte;
import com.banque.repositories.BanqueRepository;
import com.banque.services.ServiceCompte;

@Service
@Transactional
public class ServiceCompteImpl implements ServiceCompte {

	private final BanqueRepository banqueService;

	public ServiceCompteImpl(BanqueRepository banqueService) {
		this.banqueService = banqueService;
	}

	@Override
	public List<Compte> getAllComptes() {
		return banqueService.findAll();
	}

	@Override
	public Compte addCompte(Compte cp) {
		return banqueService.save(cp);
	}

	@Override
	public void updateCompte(Compte cp) {
		banqueService.updateCompte(cp.getId(), cp.getSoldeCompte(), cp.getDateCreation(), cp.getUserId());
	}

	@Override
	public void deleteCompte(String code) {
		List<Compte> allComptes = banqueService.findAll();
		for (Compte c : allComptes) {
			if (c.getNumeroCompte().equals(code)) {
				Long id = c.getId();
				Optional<Compte> cp = banqueService.findById(id);
				if (cp.isPresent()) {
					banqueService.delete(cp.get());
				}
			}
		}
	}

	@Override
	public List<Compte> getComptesById(int id) {
		return banqueService.findCompteByUserId(id);		
	}

}
