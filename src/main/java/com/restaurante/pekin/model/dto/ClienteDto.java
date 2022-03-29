package com.restaurante.pekin.model.dto;

import java.util.HashSet;
import java.util.Set;

import com.restaurante.pekin.model.Role;

public class ClienteDto {

	private Long idCliente;
	private String username;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private boolean activo;
	private String password;
	private Set<Role> authorities = new HashSet<>();
	
	public Long getIdCliente() {
		return idCliente;
	}
	public String getUsername() {
		return username;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public boolean isActivo() {
		return activo;
	}
	public String getPassword() {
		return password;
	}
	public Set<Role> getAuthorities() {
		return authorities;
	}
	
	
}
