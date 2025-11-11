package edu.marketplace.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido = new Date();

    private double valorPedido;

    @ManyToOne
    @JoinColumn(name = "comprador_id", nullable = false)
    private UsuarioModel comprador;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    private LojaModel loja;

    @ManyToMany
    @JoinTable(
        name = "pedidos_ofertas",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "oferta_id")
    )
    private List<OfertaModel> ofertas;
}
