package edu.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoOfertaResponseDTO {
    private int id;
    private String nomeItem;
    private double precoUnitario;
    private int quantidade;
    private double precoTotal;
}