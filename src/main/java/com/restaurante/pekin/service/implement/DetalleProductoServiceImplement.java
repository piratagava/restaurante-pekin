package com.restaurante.pekin.service.implement;

import com.restaurante.pekin.exceptions.detalleProducto.DetalleMenuException;
import com.restaurante.pekin.model.DetalleProducto;
import com.restaurante.pekin.service.DetalleProductoService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.restaurante.pekin.repository.DetalleMenuRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author hernan
 */
@Service
public class DetalleProductoServiceImplement implements DetalleProductoService {

	private static final Logger log = LogManager.getLogger(DetalleProductoServiceImplement.class);

	@Autowired
	private DetalleMenuRepository detalleMenuRepository;

	@Override
	public void guardarDetallePedidoCliente(DetalleProducto detalleProducto) throws DetalleMenuException {
		StringBuilder mensaje = new StringBuilder();
		try {
			if (detalleProducto.getNombre().trim().isEmpty() || detalleProducto.getCalle().trim().isEmpty()
					|| detalleProducto.getFechahoracaptura().trim().isEmpty()
					|| detalleProducto.getLocalidad().trim().isEmpty() || detalleProducto.getPedido().trim().isEmpty()
					|| detalleProducto.getReferencia().trim().isEmpty()
					|| detalleProducto.getTelefono().trim().isEmpty() || detalleProducto.getTotalpago() < 0
					|| detalleProducto.getActivo().trim().isEmpty()) {
				log.warn("UNO DE LOS ATRIBUTOS VIENE EN NULL DE LA UBICACION");
				throw new DetalleMenuException("LOS DATOS EN NULL SON:" + detalleProducto.toString());
			} else {
				detalleMenuRepository.save(detalleProducto);
			}
		} catch (DetalleMenuException ex) {
			mensaje.append("NO SE PUDO GUARDAR DETALLE DEL PEDIDO DEL CLIENTE ERROR EN EL CATCH")
					.append(ex.getMessage());
			log.error("ENTRO EN EL CATCH DE LA CLASE DETALLE MENU SERVICE IMPLEMENT GUARDAR DATOS DETALLES");
			throw new DetalleMenuException(ex.getMessage());
		}
	}

	@Override
	public List<DetalleProducto> getActivoDetalle() throws DetalleMenuException {
		return detalleMenuRepository.listaDetallesActivos();
	}

	@Override
	public void upadteDetallePedidoEntregado(Integer idDetalle) throws DetalleMenuException {
		detalleMenuRepository.actualizarPedidoParaMostrarSoloFaltantesPorEntregar(idDetalle);
	}

}
