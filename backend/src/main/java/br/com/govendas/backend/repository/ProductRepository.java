package br.com.govendas.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.govendas.backend.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, JpaRepository<Product, Long> {
	
	@Query(value = "SELECT * FROM PRODUTO WHERE ID=?1", nativeQuery = true)
	Product findById(long id);
	
	@Query(value = "SELECT * FROM PRODUTO WHERE SKU=?1", nativeQuery = true)
	Product findSku(String sku);

	@Query(value = "SELECT * FROM PRODUTO WHERE NOME LIKE %?1%", nativeQuery = true)
	List<Product> findByName(String nome);

}
