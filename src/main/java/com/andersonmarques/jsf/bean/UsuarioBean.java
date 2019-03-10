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
		
		context.addMessage(null, new FacesMessage("Usuário inválido."));
		return null;
	}

	/**
	 * Adiciona o usuário em uma variável disponível para toda sessão
	 */
	private void addUsuarioLogadoNaMemoria() {
		//Acessa variaveis disponiveis a toda sessão a nivel do Servlet
		context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
	}
	
	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return RedirecionarPagina.destino("login");
	}
}
