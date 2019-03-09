package com.andersonmarques.jsf.bean;

import javax.faces.bean.ManagedBean;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean
public class UsuarioBean {
	
	private DAO<Usuario> usuarioDAO = new DAO<>(Usuario.class); 
	private Usuario usuario = new Usuario();
	
	public void login() {
		System.out.println("Verificando validade do usuário");
		//usuarioDAO.isUsuarioCadastrado(usuario);
	}
	
}
