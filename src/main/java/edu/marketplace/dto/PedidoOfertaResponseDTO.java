package edu.marketplace.dto;

import lombok.Data;

@Data
public class PedidoOfertaResponseDTO {
    private int id;
    private String nomeItem;
    private double precoUnitario;
    private int quantidade;
    private double precoTotal;
}