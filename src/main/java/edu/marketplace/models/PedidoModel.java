package edu.marketplace.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pedidos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(nullable = false)
    private double valorPedido;

    @ManyToOne
    @JsonIgnoreProperties({"lojas", "pedidos"})
    @JoinColumn(name = "comprador_id", nullable = false)
    private UsuarioModel comprador;

    @ManyToOne
    @JoinColumn(name = "loja_id", nullable = false)
    @JsonIgnoreProperties({"pedidos", "ofertas", "proprietario"})
    private LojaModel loja;

    @ManyToMany
    @JoinTable(
        name = "pedidos_ofertas",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "oferta_id")
    )
    @JsonIgnoreProperties({"loja"})
    private List<OfertaModel> ofertas;
}
