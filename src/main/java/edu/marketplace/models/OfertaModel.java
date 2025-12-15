package edu.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    @JsonIgnoreProperties({"ofertas", "pedidos", "proprietario"})
    private LojaModel loja;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnoreProperties({"ofertas"})
    private ItemModel item;
}
