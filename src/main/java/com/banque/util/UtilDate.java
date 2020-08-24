package com.banque.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilDate {

	private UtilDate() {

	}

	public static String utilDateMethode(String dateDerniereConnexion) {

		DateTimeFormatter pattenDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.FRENCH);

		String heureDerniereConnexion = dateDerniereConnexion.substring(11, dateDerniereConnexion.length());		
		LocalDateTime localDateTime = LocalDateTime.parse(dateDerniereConnexion, pattenDate);
		dateDerniereConnexion = localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
		heureDerniereConnexion = heureDerniereConnexion.replace(":", "h");		
		return dateDerniereConnexion + " à " + heureDerniereConnexion;

	}
}
