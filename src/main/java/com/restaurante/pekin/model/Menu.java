package com.restaurante.pekin.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hernan
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column
   private int idMenu;

   
   @Column(length=10485760)
   private String foto;

   @Column(name = "nombre")
   @NotNull(message = "Campo no debe ir vacio a la base")
   private String nombre;

   @Column(name = "descripcion")
   @NotNull(message = "Campo no debe ir vacio a la base")
   private String descripcion;

   @Column(name = "precio")
   @NotNull(message = "Campo no debe ir vacio a la base")
   private Double precio;

   public int getIdMenu() {
      return idMenu;
   }

   public void setIdMenu(int idMenu) {
      this.idMenu = idMenu;
   }

   public String getFoto() {
      return foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public Double getPrecio() {
      return precio;
   }

   public void setPrecio(Double precio) {
      this.precio = precio;
   }

   @Override
   public String toString() {
      return "Menu{" + "idMenu=" + idMenu + ", foto=" + foto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + '}';
   }

}
