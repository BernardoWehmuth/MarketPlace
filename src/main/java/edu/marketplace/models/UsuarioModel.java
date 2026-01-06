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
@Table(name = "usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"proprietario", "pedidos", "ofertas"})
    private List<LojaModel> lojas;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"comprador", "loja", "ofertas"})
    private List<PedidoModel> pedidos;
}
