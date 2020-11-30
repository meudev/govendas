package br.com.govendas.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.govendas.backend.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>, JpaRepository<Order, Long> {


	@Query(value = "SELECT * FROM PEDIDO WHERE ID=?1", nativeQuery = true)
	Order findById(long id);
		
	@Query(value = "SELECT * FROM PEDIDO WHERE NOME LIKE %?1%", nativeQuery = true)
	List<Order> findByName(String nome);


	
}
