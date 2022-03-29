package com.restaurante.pekin.service;

import com.restaurante.pekin.exceptions.menu.MenuException;
import com.restaurante.pekin.model.Menu;
import java.util.List;

/**
 *
 * @author hernan
 */
public interface MenuService {
   
   public void guardar(Menu menu) throws MenuException;
   
   public List<Menu> getAllMenu() throws MenuException;
   
   public void eliminarMenu(Integer idMenu) throws MenuException;
}
