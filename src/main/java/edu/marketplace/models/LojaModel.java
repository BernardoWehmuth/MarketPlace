package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "loja")
public class LojaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel proprietario;

    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;

    @ManyToMany
    @JoinTable(
        name = "loja_itens",
        joinColumns = @JoinColumn(name = "loja_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemModel> itens;
}
