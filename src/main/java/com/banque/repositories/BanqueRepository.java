package com.banque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banque.entites.Compte;


@Repository
public interface BanqueRepository extends JpaRepository<Compte, Long>{  
		
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Compte c SET"	 		
	 		+ " c.soldeCompte =:soldeCompte, "
	 		+ " c.dateCreation=:dateCreation "
	 		+ " WHERE c.id =:idCompte"
	 		+ " AND c.userId =:userId")
	void updateCompte(
			 @Param("idCompte") long id,			
			 @Param("soldeCompte") double soldeCompte,
			 @Param("dateCreation") String dateCreation,
			 @Param("userId") int userId);
	
	List<Compte> findCompteByUserId(int userId);
	
}
