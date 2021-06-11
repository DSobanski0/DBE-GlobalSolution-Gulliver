package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.ParqueDao;
import br.com.fiap.model.Parque;

@Named
@RequestScoped
public class ParqueBean {

	private Parque parque = new Parque();
	
	private ParqueDao dao = new ParqueDao();

	public void salvar() {
		dao.salvar(parque);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parque cadastrado com sucesso"));
	}
	
	public List<Parque> getParques() {
		return dao.listar();
	}
	
	public Parque getParque() {
		return parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}
}