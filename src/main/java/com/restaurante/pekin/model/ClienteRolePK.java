package com.restaurante.pekin.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;


public class ClienteRolePK implements Serializable {

	private static final long serialVersionUID = 6342586275951743721L;

	@Column(name = "id_cliente")
	private int idCliente;

	@Column(name = "name")
	private String name;

	 public ClienteRolePK() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + Objects.hashCode(this.idCliente);
		hash = 31 * hash + Objects.hashCode(this.name);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClienteRolePK other = (ClienteRolePK) obj;
		if (!Objects.equals(this.name, other.name)) {
			return false;
		}
		if (!Objects.equals(this.idCliente, other.idCliente)) {
			return false;
		}
		return true;
	}

	public ClienteRolePK(int idCliente, String name) {
		super();
		this.idCliente = idCliente;
		this.name = name;
	}

}
