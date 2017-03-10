package br.ufpi.carrinhoCompras.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	private String bairro;
	private String logradouro;
	private int numero;
	
	public Endereco() {
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
