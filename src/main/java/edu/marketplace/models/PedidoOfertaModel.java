package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pedido_oferta")
@Data
public class PedidoOfertaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "oferta_id", nullable = false)
    private OfertaModel oferta;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private double precoUnitario;
}
