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
@Table(name="pedido")
@Getter@Setter
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="cliente", nullable= false)
	private Long cliente;
	
	@Column(name="totalCompra", nullable= false)
	private int totalCompra;
	
	@Column(name="dataCompra", nullable= false)
	private String dataCompra;
	
}