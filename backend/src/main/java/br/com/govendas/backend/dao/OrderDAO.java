package br.com.govendas.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.govendas.backend.model.Order;
import br.com.govendas.backend.repository.OrderRepository;

@Service
public class OrderDAO {
	
	@Autowired
	OrderRepository repository;

	public Order save(Order order) {
		Order novoOrder = null;
		
		try {
			return novoOrder = repository.save(order);
		} catch (Exception e) {
			e.printStackTrace();
			return novoOrder;
		}
		
	}

	public List<Order> findByName(String nome) {
		List<Order> order = null;

		try {
			return order = repository.findByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return order;
		}
	}
	
	public Order findById(long id) {
		Order order = null;
		
		try {
			return order = repository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return order;
		}
	}
	
	public List<Order> findAll() {

		List<Order> orders = null;

		try {
			return orders = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return orders;
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

