package com.andersonmarques.jsf.bean;

import javax.faces.bean.ManagedBean;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;

@SuppressWarnings("deprecation")
@ManagedBean
public class AutorBean {
	
	private Autor autor = new Autor();
	
	public Autor getAutor() {
		return autor;
	}
	
	public void gravar() {
		new DAO<Autor>(Autor.class).gravar(autor);
		autor = new Autor();
	}
}
