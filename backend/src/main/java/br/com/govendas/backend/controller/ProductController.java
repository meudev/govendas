package br.com.govendas.backend.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.govendas.backend.dao.ProductDAO;
import br.com.govendas.backend.i18n.Messages;
import br.com.govendas.backend.i18n.MessagesProperties;
import br.com.govendas.backend.model.Product;
import br.com.govendas.backend.rest.ApiResponse;
import br.com.govendas.backend.rest.ResponseEntityUtil;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	Messages message;
	
	@Autowired
	ProductDAO dao;

	@PostMapping(value = "/productSave", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Product produto) {
		
		try {
			Product novoProduto = produto;
			
			if(produto==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
			} else {
				if(novoProduto.getNome().equalsIgnoreCase("") || novoProduto.getSku().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), produto);
				} else {
					novoProduto = dao.save(produto);
					
					if(novoProduto != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_SUCESS), produto);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), novoProduto);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
		}

	}
	
	@GetMapping(value = "/productFindById", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findById(@RequestParam("id") long id) {
		Product produto = null;
		
		try {
			produto = dao.findById(id);
			
			if( produto == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), produto);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),produto);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
		}
	}
	
	@GetMapping(value = "/productFindBySku", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findByCpf(@RequestParam("sku") String sku) {
		Product produto = null;
		
		try {
			produto = dao.findSku(sku);
			
			if( produto == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), produto);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),produto);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
		}
	}
	
	@GetMapping(value = "/productFindByName", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findByName(@RequestParam("nome") String nome) {
		List<Product> produto = null;
		
		try {
			produto = dao.findByName(nome);
			
			if( produto == null || produto.isEmpty() ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), produto);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),produto);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
		}
	}
	
	@GetMapping(value = "/productFindAll", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findAll() {
			
		List<Product> retornoBusca;
		
		try {
			
			retornoBusca = dao.findAll();
			
			if(retornoBusca != null && !retornoBusca.isEmpty()) {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED), retornoBusca);
			} else {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), "");
			}

		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), "");
		}
		
	}
	
	@PatchMapping(value = "/productUpdate", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated Product produto) {
		try {
			Product updateProduto = produto;
			
			if(updateProduto==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), updateProduto);
			} else {
				if(updateProduto.getNome().equalsIgnoreCase("") || updateProduto.getSku().equals("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateProduto);
				} else {
					updateProduto = dao.save(updateProduto);
					
					if(updateProduto != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_UPDATED), updateProduto);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateProduto);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), produto);
		}

	}
	
	@DeleteMapping(value = "/productDelete", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> delete(@RequestParam("id") Long id) {
		boolean resultado = false;
		
		try {
			resultado = dao.delete(id);
			
			if( !resultado ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), null);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_DELETE), null);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), null);
		}
	}
	
}
