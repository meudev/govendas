package br.com.govendas.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Getter@Setter
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome", nullable= false)
	private String nome;
	
	@Column(name="cpf", nullable= false)
	private String cpf;
	
	@Column(name="dataNascimento", nullable= false)
	private String dataNascimento;
	
}
