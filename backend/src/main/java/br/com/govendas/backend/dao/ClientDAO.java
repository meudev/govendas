package br.com.govendas.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.govendas.backend.model.Client;
import br.com.govendas.backend.repository.ClientRepository;

@Service
public class ClientDAO {
	
	@Autowired
	ClientRepository repository;

	public Client saveClient(Client cliente) {
		Client novoCliente = null;
		
		try {
			return novoCliente = repository.save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return novoCliente;
		}
		
	}

	public Client findCpf(String cpf) {
		Client cliente = null;

		try {
			return cliente = repository.findCpf(cpf);
		} catch (Exception e) {
			e.printStackTrace();
			return cliente;
		}
	}
	
	public List<Client> findByName(String nome) {
		List<Client> cliente = null;

		try {
			return cliente = repository.findByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return cliente;
		}
	}
	
	public Client findById(long id) {
		Client cliente = null;
		
		try {
			return cliente = repository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return cliente;
		}
	}
	
	public List<Client> findAll() {

		List<Client> clientes = null;

		try {
			return clientes = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return clientes;
		}
	}

	public boolean delete(Long id) {

		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
