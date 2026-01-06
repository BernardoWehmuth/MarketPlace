package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lojas")
public class LojaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"loja", "item"})
    private List<OfertaModel> ofertas;
    
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    @JsonIgnoreProperties({"lojas", "pedidos"})
    private UsuarioModel proprietario;

    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"loja", "comprador", "ofertas"})
    private List<PedidoModel> pedidos;
}
