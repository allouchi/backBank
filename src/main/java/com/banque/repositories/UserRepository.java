package com.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banque.entites.User;

public interface UserRepository extends JpaRepository<User, Long>{
		
	User findByMailAndPassword(String mail, String password);
	User findByMail(String mail);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User c SET"	 		
	 		+ " c.lastConnection =:lastConnection "	 		
	 		+ " WHERE c.userId =:userId")	 		
	void updateUserDateLastConnection(
			 @Param("lastConnection") String lastConnection,			
			 @Param("userId") long userId);

}
