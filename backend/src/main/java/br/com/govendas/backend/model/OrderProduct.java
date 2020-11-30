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
@Table(name="pedidoTemProdutos")
@Getter@Setter
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="pedido", nullable= false)
	private Long pedido;
	
	@Column(name="produto", nullable= false)
	private Long produto;
	
	@Column(name="quantidade", nullable= false)
	private int quantidade;
	
	@Column(name="precoUnidade", nullable= false)
	private int precoUnidade;
	
}