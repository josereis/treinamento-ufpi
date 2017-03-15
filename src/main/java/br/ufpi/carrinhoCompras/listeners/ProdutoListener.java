package br.ufpi.carrinhoCompras.listeners;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import br.ufpi.carrinhoCompras.model.Produto;
import br.ufpi.carrinhoCompras.util.MensagensManager;

public class ProdutoListener {
	
	@PrePersist
	public void antesAdicionarProduto(Produto produto) {
		if(produto.getPreco() > 20){
			System.out.println(MensagensManager.readMessage("warning.preco"));
		}
	}
	
	@PostPersist
	public void depoisAdicionarProduto(Produto produto) {
		if(produto.getPreco() < 20){
			System.out.println("O produto foi salvo com sucesso!!");
		}
	}
}
