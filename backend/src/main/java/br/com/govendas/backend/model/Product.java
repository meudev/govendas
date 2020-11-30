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
@Table(name="produto")
@Getter@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="sku", nullable= false)
	private String sku;
	
	@Column(name="nome", nullable= false)
	private String nome;
	
	@Column(name="descricao", nullable= false)
	private String descricao;
	
	@Column(name="preco", nullable= false)
	private int preco;
	
	@Column(name="quantidade", nullable= false)
	private int quantidade;
	
}