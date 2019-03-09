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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String login() {
		
		if(usuarioDAO.isUsuarioCadastrado(usuario)) {
			return RedirecionarPagina.destino("livro");
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário inválido."));
		return null;
	}
}
