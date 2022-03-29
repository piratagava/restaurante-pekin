package com.restaurante.pekin.exceptions.menu;

import org.apache.commons.lang.exception.NestableException;

/**
 *
 * @author hernan
 */
public class MenuException extends NestableException {

   private static final long serialVersionUID = 1L;

   public MenuException(String msg) {
      super(msg);
   }
}
