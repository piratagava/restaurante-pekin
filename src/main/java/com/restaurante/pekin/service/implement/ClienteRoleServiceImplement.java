package com.restaurante.pekin.service.implement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.pekin.exceptions.cliente_role.ClienteRoleExceptions;
import com.restaurante.pekin.model.ClienteRole;
import com.restaurante.pekin.repository.ClienteRoleRepository;
import com.restaurante.pekin.service.ClienteRoleService;

@Service
public class ClienteRoleServiceImplement implements ClienteRoleService {

	private static final Logger log = LogManager.getLogger(ClienteRoleServiceImplement.class);

	@Autowired
	private ClienteRoleRepository clienteRoleRepository;

	@Override
	public void guardarClienteRole(ClienteRole clienteRole) throws ClienteRoleExceptions {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (clienteRole.getName().trim().isEmpty()) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL");
				throw new ClienteRoleExceptions("EL DATO NULL ES : " + clienteRole.getName());
			} else {
				clienteRoleRepository.save(clienteRole);
			}
		} catch (ClienteRoleExceptions e) {
			mensaje.append("NO SE PUDO GUARDAR CLIENTE ROLE ERROR EN EL CATCH").append(e.getMessage());
			log.error("ENTRO EN EL CATCH DE GUARDAR CLIENTE");
			throw new ClienteRoleExceptions(e.getMessage());
		}

	}

	@Override
	public List<Object> getclienteRole() throws ClienteRoleExceptions {
		return clienteRoleRepository.aListOfClienteRole();
	}

	@Override
	public void actualizarClienteRole(String role, int id_cliente) throws ClienteRoleExceptions {
		clienteRoleRepository.actualizarClienteRole(role, id_cliente);
	}

	@Override
	public void eliminarClienteRole(int id_cliente) throws ClienteRoleExceptions {
		clienteRoleRepository.eliminarPorIdCliente(id_cliente);
	}

}
