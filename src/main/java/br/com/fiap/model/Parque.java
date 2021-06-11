package br.com.fiap.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalTime abertura;
	private LocalTime fechamento;
	private Long cep;
	private String descricao;

	public Parque() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalTime getAbertura() {
		return abertura;
	}

	public void setAbertura(LocalTime abertura) {
		this.abertura = abertura;
	}

	public LocalTime getFechamento() {
		return fechamento;
	}

	public void setFechamento(LocalTime fechamento) {
		this.fechamento = fechamento;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Parque [nome=" + nome + ", abertura=" + abertura + ", fechamento=" + fechamento + ", cep=" + cep
				+ ", descricao=" + descricao + "]";
	}
}