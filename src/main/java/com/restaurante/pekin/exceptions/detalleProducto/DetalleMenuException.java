package com.restaurante.pekin.exceptions.detalleProducto;

import org.apache.commons.lang.exception.NestableException;

/**
 *
 * @author hernan
 */
public class DetalleMenuException extends NestableException {

   private static final long serialVersionUID = 1L;

   public DetalleMenuException(String msg) {
      super(msg);
   }
}
