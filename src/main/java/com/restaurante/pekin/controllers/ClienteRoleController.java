package com.restaurante.pekin.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurante.pekin.exceptions.cliente_role.ClienteRoleExceptions;
import com.restaurante.pekin.model.ClienteRole;
import com.restaurante.pekin.model.dto.ClienteRoleDto;
import com.restaurante.pekin.service.ClienteRoleService;



@Controller
public class ClienteRoleController {
	private static final Logger log = LogManager.getLogger(DetalleProductoController.class);

	@Autowired
	private ClienteRoleService clienteRoleService;

	private static final String ASOCIAR_CLIENTE_ROLE = "/insertarClienteRole";
	private static final String LISTAR_CLIENTE_ROLE = "/listarClienteRole";
	private static final String ACTUALIZAR_CLIENTE_ROLE = "/actualizarClienteRole";

	@PostMapping(value = ASOCIAR_CLIENTE_ROLE)
	public @ResponseBody Boolean insertarClienteRole(@RequestBody ClienteRoleDto clienteRole)
			throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		try {
			ClienteRole persistNewClienteRole = new ClienteRole();
			persistNewClienteRole.setIdCliente(clienteRole.getIdCliente());
			persistNewClienteRole.setName(clienteRole.getName());			
			clienteRoleService.guardarClienteRole(persistNewClienteRole);
			return true;
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR CLIENTE");
			return false;
		}
	}

	@GetMapping(value = LISTAR_CLIENTE_ROLE)
	public @ResponseBody List<Object> getClienteRole() throws ClienteRoleExceptions {		
		List<Object> listar = clienteRoleService.getclienteRole();
		return listar;
	}

	@PutMapping(value = ACTUALIZAR_CLIENTE_ROLE)
	public @ResponseBody Boolean actualizarClienteRole(@RequestBody ClienteRoleDto clienteRole)
			throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteRoleService.actualizarClienteRole(clienteRole.getName(), clienteRole.getIdCliente());
			return true;
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE_ROLE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE ROLE");
			return false;
		}
	}
}
