package com.p3.examenpractico.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.p3.examenpractico.controller.response.BaseResponse;
import com.p3.examenpractico.model.Cliente;
import com.p3.examenpractico.model.Pedido;
import com.p3.examenpractico.model.Vendedor;
import com.p3.examenpractico.repository.PedidoRepository;
import com.p3.examenpractico.service.PedidoService;

/**
 * The `PedidoServiceImpl` class is a service implementation that handles CRUD
 * operations for the
 * `Pedido` entity.
 * 
 * @author Eduardo Chombo
 * @version 1.0
 */
@Service
public class PedidoServiceImpl implements PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Override
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(pedidoRepository.findAll());
  }

  @Override
  public ResponseEntity<?> savePedido(Pedido pedido) {
    Cliente c = new Cliente();
    c.setId(pedido.getCliente().getId());

    Vendedor v = new Vendedor();
    v.setId(pedido.getVendedor().getId());

    pedido.setCliente(c);
    pedido.setVendedor(v);
    return ResponseEntity.ok(pedidoRepository.save(pedido));
  }

  /**
   * The function cancels a pedido (order) if it is within 10 minutes of the
   * pedido's fechaPedido
   * (order date), otherwise it returns an error message.
   * 
   * @param id The "id" parameter is an Integer representing the ID of the pedido
   *           (order) that needs to
   *           be canceled.
   * @return The method is returning a ResponseEntity object with a generic type
   *         of BaseResponse.
   */
  @Override
  public ResponseEntity<BaseResponse> cancelPedido(Integer id) {
    if (id != null) {
      Pedido p = pedidoRepository.findById(id).orElse(null);
      LocalDateTime ldt = p.getFechaPedido()
          .toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime();
      ldt.plusMinutes(10);

      LocalDateTime ldtCurrent = LocalDateTime.now();

      if (ldt.isAfter(ldtCurrent)) {
        // Si se puede cancelar
        p.setStatus("C");
        pedidoRepository.save(p);

        return ResponseEntity.ok(BaseResponse.ok());
      }

      return ResponseEntity.ok(BaseResponse.error("El pedido ya no puede ser cancelado"));
    }
    return ResponseEntity.ok(BaseResponse.error("El pedido no se encontró"));
  }

  /**
   * The function changes the status of a pedido (order) identified by its id and
   * returns the updated
   * pedido.
   * 
   * @param id     The id of the pedido (order) that needs to have its status
   *               changed.
   * @param status The "status" parameter is a String that represents the new
   *               status of a pedido
   *               (order).
   * @return The method is returning a ResponseEntity object.
   */
  @Override
  public ResponseEntity<?> cambiarStatusPedido(Integer id, String status) {
    Pedido p = pedidoRepository.findById(id).orElse(null);
    p.setStatus(status);
    return ResponseEntity.ok(pedidoRepository.save(p));
  }
}
