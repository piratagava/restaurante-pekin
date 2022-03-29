package com.restaurante.pekin.model.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author hernan
 */
public class DetalleDto {

  private int idDetalle;
  private String nombre;
  private String localidad;
  private String calle;
  private String referencia;
  private String pedido;
  private Double totalpago;
  private String telefono;
  

  public String obtieneFechaHora() {
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    return timeStamp;
  }

  public int getIdDetalle() {
    return idDetalle;
  }

  public String getNombre() {
    return nombre;
  }

  public String getLocalidad() {
    return localidad;
  }

  public String getCalle() {
    return calle;
  }

  public String getReferencia() {
    return referencia;
  }

  public String getPedido() {
    return pedido;
  }

  public Double getTotalpago() {
    return totalpago;
  }

  public String getTelefono() {
    return telefono;
  }

  @Override
  public String toString() {
    return "DetalleDto{" + "idDetalle=" + idDetalle + ", nombre=" + nombre + ", localidad=" + localidad + ", calle=" + calle + ", referencia=" + referencia + ", pedido=" + pedido + ", totalpago=" + totalpago + ", telefono=" + telefono + '}';
  }

}
