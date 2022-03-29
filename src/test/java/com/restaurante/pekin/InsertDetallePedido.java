package com.restaurante.pekin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.restaurante.pekin.controllers.DetalleProductoController;
import com.restaurante.pekin.exceptions.detalleProducto.DetalleMenuException;
import com.restaurante.pekin.model.dto.DetalleDto;

class InsertDetallePedido {

	@Test
	void test() throws DetalleMenuException {		
		DetalleProductoController insert= new DetalleProductoController();
		DetalleDto s= new DetalleDto();
		s.getCalle();
		insert.insertarClienteDetalle(s);
		assertEquals(true, insert);
	}

}
