package com.restaurante.pekin.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurante.pekin.exceptions.cliente.ClienteException;
import com.restaurante.pekin.model.Cliente;
import com.restaurante.pekin.model.dto.ClienteDto;
import com.restaurante.pekin.service.ClienteService;



@Controller
public class ClienteController {
	private static final Logger log = LogManager.getLogger(DetalleProductoController.class);
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String CLIENTE_INSERT_DATA = "/insertarCliente";
	private static final String LISTA_ALL_CLIENTE = "/listaCliente";
	private static final String UPDATE_CLIENTE = "/actualizarCliente";
	private static final String DELETE_CLIENTE = "/eliminarCliente";
	private static final String CONSULTA_ULTIMO_ID_CLIENTE_REGISTRADO = "/consultaIdClienteReciente";
	private static final String CONSULTA_CLIENTE_LOGADO = "/consultaClienteSesion";
	private static final String CONSULTA_CLIENTE_EN_DETALLE_SERVICIO = "/consultaEstaClienteEnDetalleServicio";

	@GetMapping(value = CONSULTA_ULTIMO_ID_CLIENTE_REGISTRADO)
	public @ResponseBody Integer consultaIdCliente() throws ClienteException {
		try {
			return clienteService.consultaUltimoIdClienteRegistrado();
		} catch (ClienteException e) {
			return 0;
		}
	}

	@PostMapping(value = CLIENTE_INSERT_DATA)
	public @ResponseBody Boolean insertarCliente(@RequestBody ClienteDto cliente) throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			Cliente persistNewClient = new Cliente();
			persistNewClient.setUsername(cliente.getUsername());
			persistNewClient.setApellidoPaterno(cliente.getApellidoPaterno());
			persistNewClient.setApellidoMaterno(cliente.getApellidoMaterno());
			persistNewClient.setActivo(cliente.isActivo());
			persistNewClient.setPassword(passwordEncoder.encode(cliente.getPassword()));
			persistNewClient.setAuthorities(cliente.getAuthorities());
			clienteService.guardarCliente(persistNewClient);
			return true;
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ERROR CONTROLLER").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE INSERTAR CONTROLLER CLIENTE");
			throw new ClienteException(e.getMessage());
		}
	}

	@GetMapping(value = LISTA_ALL_CLIENTE)
	public @ResponseBody List<Cliente> listarClientes() throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			return clienteService.listarClientes();
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO LISTAR CLIENTE ERROR CONTROLLER").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE LISTAR CONTROLLER CLIENTE");
			throw new ClienteException(e.getMessage());
		}
	}

	@GetMapping(value = CONSULTA_CLIENTE_EN_DETALLE_SERVICIO)
	public @ResponseBody List<Cliente> listarClientesSinoExistenEnDetalleServicio() throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			return clienteService.clienteNoAsociadosDetalleService();
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO LISTAR listarClientesSinoExistenEnDetalleServicio CONTROLLER").append(e.getMessage());			
			throw new ClienteException(e.getMessage());
		}

	}

	@DeleteMapping(value = DELETE_CLIENTE)
	// @RequestMapping(value = "/listarConsultaMateria/{id_materia}", method =
	// RequestMethod.GET)
	public @ResponseBody Boolean deleteProducto(@RequestBody ClienteDto cliente) throws ClienteException {
		// public @ResponseBody Materia consultaMaterias(@PathVariable int id_materia) {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteService.deleteCliente(cliente.getIdCliente());
			return true;
		} catch (ClienteException ex) {
			mensaje.append("deleteProducto CLIENTE ERROR INTERNO").append(ex.getMessage());		
			throw new ClienteException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_CLIENTE)
	public @ResponseBody Boolean updateCliente(@RequestBody ClienteDto cliente) throws ClienteException {
		StringBuilder mensaje = new StringBuilder();
		try {
			clienteService.actualizarCliente(cliente.getUsername(), cliente.getApellidoPaterno(),
					cliente.getApellidoMaterno(), cliente.isActivo(), passwordEncoder.encode(cliente.getPassword()),
					cliente.getIdCliente());
			return true;
		} catch (ClienteException e) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE ERROR INTERNO").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE CONTROLLER");
			throw new ClienteException(e.getMessage());
		}

	}

}
