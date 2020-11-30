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

import br.com.govendas.backend.dao.OrderDAO;
import br.com.govendas.backend.i18n.Messages;
import br.com.govendas.backend.i18n.MessagesProperties;
import br.com.govendas.backend.model.Order;
import br.com.govendas.backend.rest.ApiResponse;
import br.com.govendas.backend.rest.ResponseEntityUtil;

@RestController
@CrossOrigin
public class OrderController {
	
	@Autowired
	Messages message;
	
	@Autowired
	OrderDAO dao;

	@PostMapping(value = "/orderSave", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> save(@RequestBody Order order) {
		
		try {
			Order novoOrder = order;
			
			if(order==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), order);
			} else {
				if(novoOrder.getDataCompra().equalsIgnoreCase("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), order);
				} else {
					novoOrder = dao.save(order);
					
					if(novoOrder != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_SUCESS), order);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), novoOrder);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), order);
		}

	}
	
	@GetMapping(value = "/orderFindById", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findById(@RequestParam("id") long id) {
		Order order = null;
		
		try {
			order = dao.findById(id);
			
			if( order == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), order);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),order);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), order);
		}
	}
	

	@GetMapping(value = "/orderFindAll", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findAll() {
			
		List<Order> retornoBusca;
		
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
	
	@PatchMapping(value = "/orderUpdate", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated Order order) {
		try {
			Order updateOrder = order;
			
			if(updateOrder==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), updateOrder);
			} else {
				if(updateOrder.getDataCompra().equalsIgnoreCase("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateOrder);
				} else {
					updateOrder = dao.save(updateOrder);
					
					if(updateOrder != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_UPDATED), updateOrder);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateOrder);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), order);
		}

	}
	
	@DeleteMapping(value = "/orderDelete", produces = "application/json")
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
