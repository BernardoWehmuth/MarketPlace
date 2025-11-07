package edu.marketplace.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pedido")
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
        name = "pedido_itens",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemModel> itens;
}
