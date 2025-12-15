package edu.marketplace.dto;

import lombok.Data;

@Data
public class OfertaResponseDTO {
    private int id;
    private String nomeItem;
    private String descricaoItem;
    private double preco;
    private int quantidade;
}