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

	private DAO<Autor> autorDAO = new DAO<Autor>(Autor.class);
	private DAO<Livro> livroDAO = new DAO<Livro>(Livro.class);
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutores() {
		return autorDAO.listarTodos();
	}
	
	public void carregarLivroPorId() {
		System.out.println("Buscando livro por id");
		livro = livroDAO.buscarPorId(livroId);
	}

	public void gravarAutor() {
		Autor autor = autorDAO.buscarPorId(autorId);
		livro.adicionarAutor(autor);
	}

	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}

	public List<Livro> getLivros() {
		List<Livro> livros = livroDAO.listarTodos();
		return livros;
	}

	// Retorna o livro para a View
	public Livro getLivro() {
		return livro;
	}

	public String gravar() {
		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("O livro deve ter pelo menos um Autor");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O livro deve ter pelo menos um Autor"));
			return null;
		} else if(livro.getId() != null) {
			livroDAO.atualizar(livro);
		} else {
			livroDAO.gravar(livro);
			livro = new Livro();
		}

		return RedirecionarPagina.destino("livro");
	}
	
	public void editar(Livro livro) {
		this.livro = livro;
	}
	
	public void removerAutorLivro(Autor autor) {
		livro.removerAutor(autor);
	}
	
	public void remover(Livro livro) {
		livroDAO.remover(livro.getId());
	}
	
	public String formAutor() {
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
			throw new ValidatorException(new FacesMessage("ISBN deve começar com 1"));
		}
	}
}
