package com.andersonmarques.jsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.util.RedirecionarPagina;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class AutorBean {
	
	private DAO<Autor> autorDAO = new DAO<Autor>(Autor.class);
	private Autor autor = new Autor();
	
	public Autor getAutor() {
		return autor;
	}
	
	public String gravar() {
		autorDAO.gravar(autor);
		autor = new Autor();
		
		return RedirecionarPagina.destino("autor");
	}
	
	public String formLivro() {
		//Redireciona para livro.xhtml
		return RedirecionarPagina.destino("livro");
	}
	
	public List<Autor> getAutores() {
		return autorDAO.listarTodos();
	}
}
