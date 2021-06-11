package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;

@Named
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	UsuarioDao dao = new UsuarioDao();
	
	public void salvar() {
		dao.salvar(this.usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado com sucesso"));
	}
	
	public List<Usuario> getUsuarios() {
		return dao.listar();
	}
	
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		boolean exist = dao.exist(usuario);
		if(exist) {
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "index?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", ""));
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuario");
		return "login?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}