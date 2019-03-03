package com.andersonmarques.jsf.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.model.Livro;
import com.andersonmarques.jsf.util.RedirecionarPagina;

//Informa que a classe é gerenciada pelo JSF
@SuppressWarnings("deprecation")
@ViewScoped // Preserva o livroBean enquanto a tela .xhtml existir
@ManagedBean
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

	public List<Livro> getLivros() {
		List<Livro> livros = new DAO<Livro>(Livro.class).listarTodos();
		return livros;
	}

	// Retorna o livro para a View
	public Livro getLivro() {
		return livro;
	}

	public String gravar() {
		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("O livro deve ter pelo menos um Autor");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("O livro deve ter pelo menos um Autor"));
		}
		
		new DAO<Livro>(Livro.class).gravar(livro);
		livro = new Livro();
		
		return RedirecionarPagina.destino("livro");
	}
	
	public void remover(Livro livro) {
		new DAO<Livro>(Livro.class).remover(livro.getId());
	}
	
	public String formAutor() {
		//redireciona para autor.xhtml
		return RedirecionarPagina.destino("autor");
	}
	
	/**
	 * Validação personalizada.
	 * @param facesContext
	 * @param component
	 * @param value
	 */
	public void comecaComDigitoUm(FacesContext facesContext, UIComponent component, Object value) {
		if(!value.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
		}
	}
}
