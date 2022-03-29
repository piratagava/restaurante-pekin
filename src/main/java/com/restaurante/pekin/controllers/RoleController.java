package com.restaurante.pekin.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restaurante.pekin.exceptions.role.RoleException;
import com.restaurante.pekin.model.Role;
import com.restaurante.pekin.model.dto.RoleDto;
import com.restaurante.pekin.service.RoleService;


@Controller
public class RoleController {

	private static final Logger log = LogManager.getLogger(MenuController.class);

	@Autowired
	private RoleService roleService;

	private static final String ROLE_INSERT_DATA = "/insertarRole";
	private static final String LISTA_ALL_ROLE = "/listarRole";
	private static final String DELETE_ROLE = "/eliminarRole";
	private static final String UPDATE_ROLE = "/actualizarRole";
	private static final String CONSULTA_ID_ROLE = "/consultarIdRole";

	@GetMapping(value = CONSULTA_ID_ROLE)
	public @ResponseBody Integer consultaRole() throws RoleException {
		try {
			if (roleService.consultaId() == null || roleService.consultaId() == 0) {
				return 0;
			} else
				return roleService.consultaId();
		} catch (RoleException e) {
			return 0;
		}
	}

	@PostMapping(value = ROLE_INSERT_DATA)
	public @ResponseBody Boolean insertarRoles(@RequestBody RoleDto role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			Role persistNewRole = new Role();
			persistNewRole.setIdRole(role.getIdRole());
			persistNewRole.setName(role.getName());
			roleService.guardarRole(persistNewRole);
			return true;
		} catch (RoleException e) {
			mensaje.append("NO SE PUDO GUARDAR ROLE ERROR insertarRoles").append(e.getMessage());
			throw new RoleException(e.getMessage());
		}
	}

	@GetMapping(value = LISTA_ALL_ROLE)
	public @ResponseBody List<Role> listarRoles() throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			List<Role> listarRoles = roleService.listarRoles();
			return listarRoles;
		} catch (RoleException e) {
			mensaje.append("NO SE PUDO LISTAR ROLE listarRoles").append(e.getMessage());
			throw new RoleException(e.getMessage());			
		}

	}

	@DeleteMapping(value = DELETE_ROLE)
	public @ResponseBody Boolean deleteRole(@RequestBody RoleDto role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			roleService.deleteRole(role.getIdRole());
			return true;
		} catch (RoleException ex) {
			mensaje.append("NO SE PUDO ELIMINAR ROLE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE BORRAR CLIENTE CONTROLLER");
			throw new RoleException(ex.getMessage());
		}
	}

	@PutMapping(value = UPDATE_ROLE)
	public @ResponseBody Boolean updateRole(@RequestBody RoleDto role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			roleService.actualizarRole(role.getName(), role.getIdRole());
			return true;
		} catch (RoleException ex) {
			mensaje.append("NO SE PUDO ACTUALIZAR CLIENTE ERROR INTERNO").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE ACTUALIZAR CLIENTE CONTROLLER");
			throw new RoleException(ex.getMessage());
		}
	}
}
