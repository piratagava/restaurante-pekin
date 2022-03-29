package com.restaurante.pekin.model.dto;

/**
 *
 * @author hernan
 */
public class MenuDto {

   private int idMenu;
   private String foto;
   private String nombre;
   private String descripcion;
   private Double precio;

   public int getIdMenu() {
      return idMenu;
   }

   public String getFoto() {
      return foto;
   }

   public String getNombre() {
      return nombre;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public Double getPrecio() {
      return precio;
   }

   @Override
   public String toString() {
      return "MenuDto{" + "idMenu=" + idMenu + ", foto=" + foto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
   }

}
