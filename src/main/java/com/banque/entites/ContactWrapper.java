package com.banque.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactWrapper {
	
	private String comment;
	private String mail;
	private String numero;
}
