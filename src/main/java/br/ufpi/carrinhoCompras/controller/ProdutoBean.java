/**
 * 
 */
package br.ufpi.carrinhoCompras.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufpi.carrinhoCompras.model.Produto;
import br.ufpi.carrinhoCompras.repository.ProdutoDao;

/**
 * @author Vanderson Moura
 *
 */
@Named
@RequestScoped
public class ProdutoBean {

	@Inject
	private ProdutoDao produtoDao;
	
	private Produto produto;
	
	public ProdutoBean() {
	}
	
	@PostConstruct
	private void init(){
		produto = new Produto();
	}
	
	public void salvaProduto(){
		produtoDao.salvar(produto);
		produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
