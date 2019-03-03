package com.andersonmarques.jsf.bean;

import javax.faces.bean.ManagedBean;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.util.RedirecionarPagina;

@SuppressWarnings("deprecation")
@ManagedBean
public class AutorBean {
	
	private Autor autor = new Autor();
	
	public Autor getAutor() {
		return autor;
	}
	
	public String gravar() {
		new DAO<Autor>(Autor.class).gravar(autor);
		autor = new Autor();
		
		return RedirecionarPagina.destino("autor");
	}
	
	public String formLivro() {
		//Redireciona para livro.xhtml
		return RedirecionarPagina.destino("livro");
	}
}
