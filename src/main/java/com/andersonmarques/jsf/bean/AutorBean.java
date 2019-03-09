package com.andersonmarques.jsf.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.util.RedirecionarPagina;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class AutorBean {
	
	private DAO<Autor> autorDAO = new DAO<Autor>(Autor.class);
	private Autor autor = new Autor();
	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPorId() {
		autor = autorDAO.buscarPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public String gravar() {
		if(autor.getId() != null) {
			autorDAO.atualizar(autor);
		} else {
			autorDAO.gravar(autor);
			autor = new Autor();
		}
		
		return RedirecionarPagina.destino("autor");
	}
	
	public String formLivro() {
		//Redireciona para livro.xhtml
		return RedirecionarPagina.destino("livro");
	}
	
	public List<Autor> getAutores() {
		return autorDAO.listarTodos();
	}
	
	public void editar(Autor autor) {
		this.autor = autor;
	}
	
	public String remover(Autor autor) {
		Integer qtdLivros = autorDAO.qtdLivroAutor(autor.getId());
		if( qtdLivros > 0) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Não é possível remover um autor que possui livro cadastrados."));
			return null;
		}
		
		autorDAO.remover(autor.getId());
		return RedirecionarPagina.destino("autor");
	}
}
