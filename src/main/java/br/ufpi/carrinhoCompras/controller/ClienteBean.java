package br.ufpi.carrinhoCompras.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufpi.carrinhoCompras.model.Cliente;
import br.ufpi.carrinhoCompras.repository.ClienteDao;

@Named
@SessionScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDao clienteDao;

	private Cliente cliente;

	private String email;
	private String senha;
	private String cpf;
	

	public ClienteBean() {
	}

	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
	}

	public void login() {
		Cliente clienteBD = clienteDao.procurarClientePorEmailSenha(this.email, this.senha);

		if (clienteBD != null) {
			try {

				Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

				sessionMap.put("usuarioLogado", clienteBD);

				FacesContext.getCurrentInstance().getExternalContext().redirect("produto.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Problema ao fazer login!", "Email e/ou senha incorretos!"));
		}
	}

	public void salvar() {
		String cpf = this.cpf.replaceAll("\\.", "").replaceAll("\\-", "");
		cliente.setCpf(cpf);

		clienteDao.salvar(cliente);
	}

	public Cliente getUsuarioLogado() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		return (Cliente) sessionMap.get("usuarioLogado");
	}

	public void logout() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("usuarioLogado");

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
