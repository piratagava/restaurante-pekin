package com.restaurante.pekin.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hernan
 */
@Entity
@Table(name = "detalle")
public class DetalleProducto implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private int idDetalle;

  @Column(name = "nombre")
  @NotNull(message = "Campo nombre no valido")
  private String nombre;

  @Column(name = "localidad")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String localidad;

  @Column(name = "calle")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String calle;

  @Column(name = "referencia")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String referencia;

  @Column(name = "pedido")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String pedido;

  @Column(name = "totalpago")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private Double totalpago;

  @Column(name = "telefono")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String telefono;

  @Column(name = "fechahoracaptura")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String fechahoracaptura;

  @Column(name = "activo")
  @NotNull(message = "Campo no debe ir vacio a la base")
  private String activo;

  public DetalleProducto() {
  }

  public DetalleProducto(int idDetalle, String nombre, String localidad, String calle, String referencia, String pedido, Double totalpago, String telefono, String fechahoracaptura, String activo) {
    this.idDetalle = idDetalle;
    this.nombre = nombre;
    this.localidad = localidad;
    this.calle = calle;
    this.referencia = referencia;
    this.pedido = pedido;
    this.totalpago = totalpago;
    this.telefono = telefono;
    this.fechahoracaptura = fechahoracaptura;
    this.activo = activo;
  }

  public int getIdDetalle() {
    return idDetalle;
  }

  public void setIdDetalle(int idDetalle) {
    this.idDetalle = idDetalle;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  public String getPedido() {
    return pedido;
  }

  public void setPedido(String pedido) {
    this.pedido = pedido;
  }

  public Double getTotalpago() {
    return totalpago;
  }

  public void setTotalpago(Double totalpago) {
    this.totalpago = totalpago;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getFechahoracaptura() {
    return fechahoracaptura;
  }

  public void setFechahoracaptura(String fechahoracaptura) {
    this.fechahoracaptura = fechahoracaptura;
  }

  public String getActivo() {
    return activo;
  }

  public void setActivo(String activo) {
    this.activo = activo;
  }

  @Override
  public String toString() {
    return "DetalleProducto{" + "idDetalle=" + idDetalle + ", nombre=" + nombre + ", localidad=" + localidad + ", calle=" + calle + ", referencia=" + referencia + ", pedido=" + pedido + ", totalpago=" + totalpago + ", telefono=" + telefono + ", fechahoracaptura=" + fechahoracaptura + ", activo=" + activo + '}';
  }

}
