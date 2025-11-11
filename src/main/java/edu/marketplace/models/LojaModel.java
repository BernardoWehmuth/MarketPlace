package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "lojas")
public class LojaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cnpj;
    
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    private List<OfertaModel> ofertas;
    
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private UsuarioModel proprietario;

    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;
}
