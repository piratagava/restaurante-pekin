package com.restaurante.pekin.service;

import java.util.List;

import com.restaurante.pekin.exceptions.cliente.ClienteException;
import com.restaurante.pekin.model.Cliente;

public interface ClienteService {

	public void guardarCliente(Cliente cliente) throws ClienteException;

	public void deleteCliente(Long idCliente) throws ClienteException;

	public Integer consultaUltimoIdClienteRegistrado() throws ClienteException;

	public void actualizarCliente(String nombre, String apellidoP, String apellidoM, Boolean activo, String password,
			Long id_cliente) throws ClienteException;

	public List<Cliente> clienteNoAsociadosDetalleService() throws ClienteException;

	public List<Cliente> listarClientes() throws ClienteException;
}
