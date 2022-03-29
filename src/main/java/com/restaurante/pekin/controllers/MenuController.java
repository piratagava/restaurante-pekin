package com.restaurante.pekin.controllers;

import com.restaurante.pekin.exceptions.menu.MenuException;
import com.restaurante.pekin.model.Menu;
import com.restaurante.pekin.model.dto.MenuDto;
import com.restaurante.pekin.service.MenuService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hernan
 */
@Controller
public class MenuController {

	private static final Logger log = LogManager.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	private static final String MENU_INSERT_DATA = "/insertarProducto";
	private static final String MENU_UPDATE_DATA = "/actualizarProducto";
	private static final String LISTA_MENU = "/listarProductos";
	private static final String LISTA_PARA_INDEX = "/listarProductosIndex";
	private static final String DELETE_MENU = "/eliminarMenu";

	@PostMapping(value = MENU_INSERT_DATA)
	public @ResponseBody Boolean insertarMenu(@RequestBody MenuDto menuDto) throws MenuException {
		StringBuilder mensaje = new StringBuilder();
		try {
			Menu menuPersist = new Menu();
			menuPersist.setFoto(menuDto.getFoto());
			menuPersist.setNombre(menuDto.getNombre());
			menuPersist.setDescripcion(menuDto.getDescripcion());
			menuPersist.setPrecio(menuDto.getPrecio());
			menuService.guardar(menuPersist);
			return true;
		} catch (MenuException ex) {
			mensaje.append("NO SE PUDO GUARDAR MENU ERROR CONTROLLER MENU").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE MENU CONTROLLER ");
			throw new MenuException(ex.getCause().toString());
		}
	}

	@PutMapping(value = MENU_UPDATE_DATA)
	public @ResponseBody Boolean updateMenu(@RequestBody MenuDto menuDto) throws MenuException {
		StringBuilder mensaje = new StringBuilder();
		try {
			Menu menuUpdate = new Menu();
			menuUpdate.setIdMenu(menuDto.getIdMenu());
			menuUpdate.setFoto(menuDto.getFoto());
			menuUpdate.setNombre(menuDto.getNombre());
			menuUpdate.setDescripcion(menuDto.getDescripcion());
			menuUpdate.setPrecio(menuDto.getPrecio());
			menuService.guardar(menuUpdate);
			return true;
		} catch (MenuException ex) {
			mensaje.append("NO SE PUDO GUARDAR MENU ERROR CONTROLLER MENU").append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE MENU CONTROLLER UPDATE");
			throw new MenuException(ex.getCause().toString());
		}
	}

	@GetMapping(value = LISTA_MENU)
	public @ResponseBody List<Menu> listarMenu() throws MenuException {
		return menuService.getAllMenu();
	}

	@GetMapping(value = LISTA_PARA_INDEX)
	public @ResponseBody List<Menu> listarMenuIndex() throws MenuException {
		return menuService.getAllMenu();
	}

	@DeleteMapping(value = DELETE_MENU)
	public @ResponseBody Boolean deleteMenu(@RequestBody MenuDto menuDto) throws MenuException {
		StringBuilder mensaje = new StringBuilder();
		try {
			menuService.eliminarMenu(menuDto.getIdMenu());
			return true;
		} catch (MenuException ex) {
			mensaje.append("ERROR EN CONTROLLER ELIMINAR MENU").append(ex.getMessage());
			throw new MenuException(ex.getMessage());
		}
	}
}
