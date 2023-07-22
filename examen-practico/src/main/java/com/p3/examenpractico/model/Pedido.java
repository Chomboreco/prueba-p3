package com.p3.examenpractico.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Pedido implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String status;

  private Date fechaPedido;

  private Date fechaEntrega;

  private String ip;

  private Double total;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "cliente_id", referencedColumnName = "id")
  private Cliente cliente;

  @ManyToMany
  List<Producto> productos;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
  private Vendedor vendedor;
}
