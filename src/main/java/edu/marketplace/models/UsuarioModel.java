package edu.marketplace.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
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
    private List<LojaModel> lojas;

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;
}
