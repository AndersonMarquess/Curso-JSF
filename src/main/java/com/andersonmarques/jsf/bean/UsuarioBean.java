package com.andersonmarques.jsf.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Usuario;
import com.andersonmarques.jsf.util.RedirecionarPagina;

@SuppressWarnings("deprecation")
@ManagedBean
public class UsuarioBean {
	
	private DAO<Usuario> usuarioDAO = new DAO<>(Usuario.class); 
	private Usuario usuario = new Usuario();
	private FacesContext context = FacesContext.getCurrentInstance();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String login() {
		
		if(usuarioDAO.isUsuarioCadastrado(usuario)) {
			addUsuarioLogadoNaMemoria();
			return RedirecionarPagina.destino("livro");
		}
		
		context.addMessage(null, new FacesMessage("Usu�rio inv�lido."));
		return null;
	}

	/**
	 * Adiciona o usu�rio em uma vari�vel dispon�vel para toda sess�o
	 */
	private void addUsuarioLogadoNaMemoria() {
		//Acessa variaveis disponiveis a toda sess�o a nivel do Servlet
		context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
	}
	
	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return RedirecionarPagina.destino("login");
	}
}
