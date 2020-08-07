package com.banque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banque.entites.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
