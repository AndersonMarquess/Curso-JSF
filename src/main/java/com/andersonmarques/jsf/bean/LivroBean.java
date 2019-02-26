package com.andersonmarques.jsf.bean;

import javax.faces.bean.ManagedBean;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Livro;

//Informa que a classe é gerenciada pelo JSF
@SuppressWarnings("deprecation")
@ManagedBean
public class LivroBean {
	
	private Livro livro = new Livro();
	
	//Retorna o livro para a View
	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		new DAO<Livro>().gravar(livro);
	}
}
