package com.restaurante.pekin.service;

import org.springframework.transaction.annotation.Transactional;

import com.restaurante.pekin.model.Cliente;
import com.restaurante.pekin.repository.ClienteRepository;
import com.restaurante.pekin.security.SecurityUtils;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteSecurityService {

	private final ClienteRepository clienteRepository;

	public ClienteSecurityService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	   @Transactional(readOnly = true)
	   public Optional<Cliente> getUserWithAuthorities() {
	      return SecurityUtils.getCurrentUsername().flatMap(clienteRepository::findOneWithAuthoritiesByUsername);
	   }
	//.flatMap(), proyecta una lista de elementos de cada elemento original y los concatena en un único stream.
	//.flatmap produce de cero a n valores de salida por cada valor de entrada
}
