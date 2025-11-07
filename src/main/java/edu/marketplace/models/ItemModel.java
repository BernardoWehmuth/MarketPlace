package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "item")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private double preco;

    @ManyToMany(mappedBy = "itens")
    private List<PedidoModel> pedidos;

    @ManyToMany(mappedBy = "itens")
    private List<LojaModel> lojas;
}
