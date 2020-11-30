package br.com.govendas.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.govendas.backend.model.Product;
import br.com.govendas.backend.repository.ProductRepository;

@Service
public class ProductDAO {
	
	@Autowired
	ProductRepository repository;

	public Product save(Product produto) {
		Product novoProduto = null;
		
		try {
			return novoProduto = repository.save(produto);
		} catch (Exception e) {
			e.printStackTrace();
			return novoProduto;
		}
		
	}

	public Product findSku(String sku) {
		Product produto = null;

		try {
			return produto = repository.findSku(sku);
		} catch (Exception e) {
			e.printStackTrace();
			return produto;
		}
	}
	
	public List<Product> findByName(String nome) {
		List<Product> produto = null;

		try {
			return produto = repository.findByName(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return produto;
		}
	}
	
	public Product findById(long id) {
		Product produto = null;
		
		try {
			return produto = repository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return produto;
		}
	}
	
	public List<Product> findAll() {

		List<Product> produtos = null;

		try {
			return produtos = repository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return produtos;
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

