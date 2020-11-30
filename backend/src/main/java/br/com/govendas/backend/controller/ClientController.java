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

import br.com.govendas.backend.dao.ClientDAO;
import br.com.govendas.backend.i18n.Messages;
import br.com.govendas.backend.i18n.MessagesProperties;
import br.com.govendas.backend.model.Client;
import br.com.govendas.backend.rest.ApiResponse;
import br.com.govendas.backend.rest.ResponseEntityUtil;

@RestController
@CrossOrigin
public class ClientController {
	
	@Autowired
	Messages message;
	
	@Autowired
	ClientDAO dao;

	@PostMapping(value = "/clientSave", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> saveUser(@RequestBody Client cliente) {
		Client clienteUsuario = null;
		
		try {
			Client clienteNovo = cliente;
			
			if(cliente==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
			} else {
				if(clienteNovo.getNome().equalsIgnoreCase("") || clienteNovo.getCpf().equals("") || clienteNovo.getDataNascimento().equalsIgnoreCase("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), cliente);
				} else {
					clienteUsuario = dao.saveClient(cliente);
					
					if(clienteUsuario != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_SUCESS), cliente);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), clienteNovo);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
		}

	}
	
	@GetMapping(value = "/clientFindById", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findById(@RequestParam("id") long id) {
		Client cliente = null;
		
		try {
			cliente = dao.findById(id);
			
			if( cliente == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), cliente);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),cliente);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
		}
	}
	
	@GetMapping(value = "/clientFindByCpf", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findByCpf(@RequestParam("cpf") String cpf) {
		Client cliente = null;
		
		try {
			cliente = dao.findCpf(cpf);
			
			if( cliente == null ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), cliente);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),cliente);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
		}
	}
	
	@GetMapping(value = "/clientFindByName", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findByName(@RequestParam("nome") String nome) {
		List<Client> cliente = null;
		
		try {
			cliente = dao.findByName(nome);
			
			if( cliente == null || cliente.isEmpty() ) {
				return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.CLI_NOT_FOUND), cliente);
			}else {
				return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_FINDED),cliente);
			}

		} catch (Exception e) {
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
		}
	}
	
	@GetMapping(value = "/clientFindAll", produces = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> findAll() {
			
		List<Client> retornoBusca;
		
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
	
	@PatchMapping(value = "/clientUpdate", produces = "application/json", consumes = "application/json")
	@ResponseBody
	@CrossOrigin
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<ApiResponse> updateUser(@RequestBody @Validated Client cliente) {
		try {
			Client updateCliente = cliente;
			
			if(updateCliente==null) {
				return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), updateCliente);
			} else {
				if(updateCliente.getNome().equalsIgnoreCase("") || updateCliente.getCpf().equals("") || updateCliente.getDataNascimento().equalsIgnoreCase("")) {
					return ResponseEntityUtil.notFoundResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateCliente);
				} else {
					updateCliente = dao.saveClient(updateCliente);
					
					if(updateCliente != null) {
						return ResponseEntityUtil.okResponseEntity(message.get(MessagesProperties.CLI_UPDATED), updateCliente);
					} else {
						return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.API_UNKNOWN_FILDS), updateCliente);
					}

				}
			}
		
		} catch (Exception e){
			return ResponseEntityUtil.unprocessableResponseEntity(message.get(MessagesProperties.ENTITY_NOT_FOUND), cliente);
		}

	}
	
	@DeleteMapping(value = "/clientDelete", produces = "application/json")
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
