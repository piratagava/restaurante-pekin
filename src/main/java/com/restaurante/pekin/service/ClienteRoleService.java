package com.restaurante.pekin.service;

import java.util.List;

import com.restaurante.pekin.exceptions.cliente_role.ClienteRoleExceptions;
import com.restaurante.pekin.model.ClienteRole;



public interface ClienteRoleService {

	public void guardarClienteRole(ClienteRole clienteRole) throws ClienteRoleExceptions;

	public List<Object> getclienteRole() throws ClienteRoleExceptions;

	public void actualizarClienteRole(String role, int id_cliente) throws ClienteRoleExceptions;

	public void eliminarClienteRole(int id_cliente) throws ClienteRoleExceptions;
}
