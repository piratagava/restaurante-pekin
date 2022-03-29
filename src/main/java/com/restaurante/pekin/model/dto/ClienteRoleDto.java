package com.restaurante.pekin.model.dto;

public class ClienteRoleDto {

	private int idCliente;
	private String name;

	public int getIdCliente() {
		return idCliente;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "ClienteRoleDto [idCliente=" + idCliente + ", name=" + name + "]";
	}

}
