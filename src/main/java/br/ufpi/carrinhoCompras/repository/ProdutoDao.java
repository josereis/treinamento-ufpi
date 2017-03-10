package br.ufpi.carrinhoCompras.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufpi.carrinhoCompras.model.Produto;

@Stateless
public class ProdutoDao {

	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Produto produto){
		em.merge(produto);
	}
	
	public void remover(Produto produto){
		em.remove(produto);
	}
	
	public List<Produto> listar(){
		Query query = em.createQuery("Select p from Produto p", Produto.class);
		return query.getResultList();
	}
}
