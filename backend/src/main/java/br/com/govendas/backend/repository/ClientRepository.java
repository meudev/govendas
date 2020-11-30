package br.com.govendas.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.govendas.backend.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>, JpaRepository<Client, Long> {
	
	@Query(value = "SELECT * FROM CLIENTE WHERE ID=?1", nativeQuery = true)
	Client findById(long id);
	
	@Query(value = "SELECT * FROM CLIENTE WHERE CPF=?1", nativeQuery = true)
	Client findCpf(String cpf);

	@Query(value = "SELECT * FROM CLIENTE WHERE NOME LIKE %?1%", nativeQuery = true)
	List<Client> findByName(String nome);

}
