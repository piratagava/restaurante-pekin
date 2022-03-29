package com.restaurante.pekin.repository;

import com.restaurante.pekin.model.DetalleProducto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hernan
 */
@Repository
public interface DetalleMenuRepository extends JpaRepository<DetalleProducto, Integer> {	
	
	@Query(value = "select * from detalle d where activo ='activo';", nativeQuery = true)	
	public List<DetalleProducto> listaDetallesActivos();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE detalle  SET activo ='desactivado' WHERE id_detalle= ?;", nativeQuery = true)
	public void actualizarPedidoParaMostrarSoloFaltantesPorEntregar(int idDetalle);

}
