package com.andersonmarques.jsf.util;

public class RedirecionarPagina {
	
	/**
	 * Redireciona para p�gina destino.
	 * 
	 * Ex. destino(home) -> redireciona para home.xhtml
	 * 
	 * @param destino
	 * @return
	 */
	public static String destino(String destino) {
		return destino+"?faces-redirect=true";
	}
	
}
