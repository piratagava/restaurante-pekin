package com.restaurante.pekin.restAuthentication;



import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.restaurante.pekin.model.Cliente;
import com.restaurante.pekin.service.ClienteSecurityService;

@Controller
public class PersonRestController {

	private final ClienteSecurityService clienteService;

	public PersonRestController(ClienteSecurityService clienteService) {
		this.clienteService = clienteService;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/api/cliente")
	public ResponseEntity<Cliente> getActualCliente() {

		Optional<Cliente> value = clienteService.getUserWithAuthorities();

		if (value.isPresent()) {
			Cliente ok = value.get();
			return ResponseEntity.ok(ok);
		}
		return (ResponseEntity<Cliente>) ResponseEntity.badRequest();

		// ResponseEntity.ok(clienteService.getUserWithAuthorities().get());
	}

}
