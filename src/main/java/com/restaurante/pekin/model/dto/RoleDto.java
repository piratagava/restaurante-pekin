package com.restaurante.pekin.model.dto;

public class RoleDto {

	private String name;
	private int idRole;

	public String getName() {
		return name;
	}

	public int getIdRole() {
		return idRole;
	}

	@Override
	public String toString() {
		return "RoleDto [name=" + name + ", idRole=" + idRole + "]";
	}

}
