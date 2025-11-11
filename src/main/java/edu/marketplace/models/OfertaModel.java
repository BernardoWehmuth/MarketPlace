package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ofertas")
public class OfertaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double preco;
    
    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private LojaModel loja;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;
}
