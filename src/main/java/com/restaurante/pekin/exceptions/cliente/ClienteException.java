package com.restaurante.pekin.exceptions.cliente;

import org.apache.commons.lang.exception.NestableException;

/**
 *
 * @author hernan
 */
public class ClienteException extends NestableException {

  private static final long serialVersionUID = 1L;

  public ClienteException(String msg) {
    super(msg);
  }

}
