package edu.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaResponseDTO {
    private int id;
    private String nomeItem;
    private String descricaoItem;
    private double precoUnitario;
    private int quantidade;
}