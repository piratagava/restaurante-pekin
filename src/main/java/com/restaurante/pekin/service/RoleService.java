package com.restaurante.pekin.service;

import java.util.List;

import com.restaurante.pekin.exceptions.role.RoleException;
import com.restaurante.pekin.model.Role;


public interface RoleService {

	public void guardarRole(Role role) throws RoleException;

	public List<Role> listarRoles() throws RoleException;

	public void actualizarRole(String role, int id_role) throws RoleException;

	public void deleteRole(int role) throws RoleException;

	public Integer consultaId() throws RoleException;
}
