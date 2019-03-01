package com.andersonmarques.jsf.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.model.Livro;

//Informa que a classe é gerenciada pelo JSF
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped//Preserva o livroBean enquanto a tela .xhtml existir
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listarTodos();
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscarPorId(autorId);
		livro.adicionarAutor(autor);
	}
	
	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}
	
	//Retorna o livro para a View
	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		if(livro.getAutores().isEmpty()) {
            throw new RuntimeException("O livro deve ter pelo menos um Autor");
        }
		
		new DAO<Livro>(Livro.class).gravar(livro);
		livro = new Livro();
	}
}
