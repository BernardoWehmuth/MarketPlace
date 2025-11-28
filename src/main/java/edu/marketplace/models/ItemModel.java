package edu.marketplace.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "itens")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "item")
    @JsonIgnoreProperties({"item", "loja"})
    private List<OfertaModel> ofertas;
}
