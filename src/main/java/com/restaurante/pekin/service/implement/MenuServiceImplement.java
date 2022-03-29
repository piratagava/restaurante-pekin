package com.restaurante.pekin.service.implement;

import com.restaurante.pekin.exceptions.menu.MenuException;
import com.restaurante.pekin.model.Menu;
import com.restaurante.pekin.repository.MenuRepository;
import com.restaurante.pekin.service.MenuService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hernan
 */
@Service
public class MenuServiceImplement implements MenuService {

   private static final Logger log = LogManager.getLogger(MenuServiceImplement.class);

   @Autowired
   private MenuRepository menuRepository;

   @Override
   public void guardar(Menu menu) throws MenuException {
      StringBuilder mensaje = new StringBuilder();
      try {
         if (menu.getFoto().trim().isEmpty() || menu.getNombre().trim().isEmpty() || menu.getDescripcion().trim().isEmpty() || menu.getPrecio() < 0) {
            log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL DEL MENU");
            throw new MenuException("LOS DATOS EN NULL SON:" + menu.toString());
         } else {
            menuRepository.save(menu);
         }
      } catch (MenuException ex) {
         mensaje.append("NO SE PUDO GUARDAR DETALLES DEL CLIENTE ERROR EN EL CATCH").append(ex.getMessage());
         log.error("ENTRO EN EL CATCH DE LA CLASE MENU SERVICE IMPLEMENT GUARDAR DATOS DEL MENU");
         throw new MenuException(ex.getMessage());
      }

   }

   @Override
   public List<Menu> getAllMenu() throws MenuException {
      List<Menu> listaMenu = menuRepository.findAll();
      if (listaMenu.isEmpty()) {
         log.warn("LA LISTA DE DATOS DEL MENU ESTA VACIA");
         throw new MenuException("NO EXISTEN REGISTROS EN LA BASE DE DATOS AUN DEL MENU");
      }
      return listaMenu;
   }

   @Override
   public void eliminarMenu(Integer idMenu) throws MenuException {
      menuRepository.deleteById(idMenu);
   }

 

}
