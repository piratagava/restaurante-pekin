package com.restaurante.pekin.service.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.pekin.exceptions.role.RoleException;
import com.restaurante.pekin.model.Role;
import com.restaurante.pekin.repository.RoleRepository;
import com.restaurante.pekin.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService {

	private static final Logger log = LogManager.getLogger(RoleServiceImplement.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void guardarRole(Role role) throws RoleException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (role.getName().trim().isEmpty() || role.getName().length() > 40) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE VACIO O SE PASO DE LIMITE DE CARACTERES");
				throw new RoleException("EL DATO VACIO ES :" + role.getName() + " O ES MUCHOS CARACTERES");
			} else
				roleRepository.save(role);
		} catch (RoleException e) {
			mensaje.append("NO SE PUDO GUARDAR EL ROLE ERROR INTERNO").append(e.getMessage());
			log.error("ERROR INTERNO ENTRO EN EL CATCH DE GUARDAR ROLE");
			throw new RoleException(e.getMessage());
		}
	}

	@Override
	public List<Role> listarRoles() throws RoleException {
		List<Role> guardarLista = roleRepository.findAll();
		return guardarLista;
	}

	@Override
	public Integer consultaId() throws RoleException {
		return roleRepository.consultaIdRoleTotal();

	}

	@Override
	public void deleteRole(int role) throws RoleException {
		roleRepository.eliminarPorIdRole(role);
	}

	@Override
	public void actualizarRole(String role, int id_role) throws RoleException {
		roleRepository.actualizarPorNombreRole(role, id_role);
	}

}
