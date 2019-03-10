package com.andersonmarques.jsf.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.andersonmarques.jsf.model.Usuario;

public class AutorizacaoPhaseListner implements PhaseListener {
	private FacesContext context;
	private static final long serialVersionUID = 1L;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		String paginaLogin = "/login.xhtml";
		
		//Se estiver tentando acessar a página de login ou já estiver autenticado, o request segue normal.
		if(nomePagina.equals(paginaLogin)|| isUsuarioLogado()) {
			return;
		}
		
		//caso contrario faz a navegação para página de login.
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, paginaLogin);
	}

	private boolean isUsuarioLogado() {
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuario != null;
	}

	@Override
	public void beforePhase(PhaseEvent event) { }

	@Override
	public PhaseId getPhaseId() {
		//Aplicado apenas na primeira parte do ciclo de vida.
		return PhaseId.RESTORE_VIEW;
	}
}
