package com.banque.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="nom_compte")
	private String nomCompte;
	
	@Column(name="numero_compte")
	private String numeroCompte;
	
	@Column(name="type_compte")
	private String typeCompte;
	
	@Column(name="solde_compte")
	private double soldeCompte;
	
	@Column(name="date_Creation")
	private String dateCreation;
	
	@Column(name="user_id")
	private int userId;
		
	
}	
